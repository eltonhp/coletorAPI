package br.com.dito.coletaAPI.service;

import br.com.dito.coletaAPI.model.Product;
import br.com.dito.coletaAPI.model.Purchase;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON;

/**
 * @author Elton H. Paula
 */
@Service
public class PurchaseService {
    private WebClient eventAPI = WebClient.create("https://storage.googleapis.com");

    public Mono<HashMap<String, List>> purchaseTimeLine() {
      Flux<LinkedHashMap> eventList =  this.eventAPI
                .get()
                .uri("/dito-questions/events.json")
                .accept(APPLICATION_JSON)
                .exchange()
                .flatMapMany(response -> response.bodyToFlux(LinkedHashMap.class));
      return this.getPurchaseGroupByTransactionId(eventList);
    }


    /**
     * criar uma lista de compras agrupado por transaction_Id
     * @param events
     *        eventos recebido do eventAPI
     * @return Lista de compras por timeline
     */
    private Mono<HashMap<String, List>> getPurchaseGroupByTransactionId(Flux<LinkedHashMap> events) {
        return events.collectList().map(t -> {
            HashMap<String, List> transactionEventsMap = new HashMap<String, List>();

            ArrayList<LinkedHashMap> eventList = (ArrayList<LinkedHashMap>) t.get(0).get("events");
            eventList.stream().forEach(value -> {
                ArrayList<LinkedHashMap<String, String>> customDataList =  (ArrayList<LinkedHashMap<String, String>>)value.get("custom_data");
                LinkedHashMap<String, String> transactionMap = customDataList.stream().filter(data -> data.get("key").equals("transaction_id"))
                                                               .findAny().orElse(null);
                if(transactionMap != null) {
                    if(transactionEventsMap.get(transactionMap.get("value")) == null) {
                        transactionEventsMap.put(transactionMap.get("value"), new ArrayList());
                    }
                    transactionEventsMap.get(transactionMap.get("value")).add(value);
                }
            });

            HashMap<String, List> m = new LinkedHashMap();
            m.put("timeline", this.groupPurchase(transactionEventsMap));
            return m;
        });

    }

    private List<Purchase> groupPurchase(HashMap<String, List> transactionEventsMap ) {
        List<Purchase> purchaseList =  transactionEventsMap.entrySet().parallelStream().map(entry -> {
            Purchase p = new Purchase();
            p.setTransactionId(Long.valueOf(entry.getKey()));
            entry.getValue().stream().forEach( value -> {
                LinkedHashMap valueMap = (LinkedHashMap) value;
                List<LinkedHashMap> list =   (ArrayList<LinkedHashMap>) valueMap.get("custom_data");
                if (list != null && valueMap.get("event").equals("comprou")) {

                     list.parallelStream().forEach(keySet -> {
                         if (keySet.get("key").equals("store_name")) {
                             p.setStoreName((String)keySet.get("value"));
                         }
                     });

                    String storeName =  (String) list.parallelStream().filter(entrSet -> entrSet.get("key")
                                                                 .equals("store_name")).findFirst()
                                                                 .orElse(null).get("value");

                    p.setTimestamp((String)valueMap.get("timestamp"));
                    p.setStoreName(storeName);
                }

                if(list != null && valueMap.get("event").equals("comprou-produto")) {
                    if(p.getProducts() == null) {
                        p.setProducts(new ArrayList<Product>());
                        p.setRevenue(BigDecimal.ZERO);
                    }

                   Product product = new Product();
                    list.parallelStream().forEach(r -> {
                        if(r.get("key").equals("product_name")) {
                            product.setName((String)r.get("value"));
                        }
                        if(r.get("key").equals("product_price")) {
                            product.setPrice(new BigDecimal(((Number)r.get("value")).doubleValue()).setScale(2, BigDecimal.ROUND_UP));
                        }
                    });

                    p.getProducts().add(product);
                    p.setRevenue(p.getProducts().parallelStream().map(Product::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add));
                }
            });

            return p;
        }).collect(Collectors.toList());
        return purchaseList;
    }
}