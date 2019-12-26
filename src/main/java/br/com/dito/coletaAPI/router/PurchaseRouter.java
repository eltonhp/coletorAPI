package br.com.dito.coletaAPI.router;


import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Component
public class PurchaseRouter {

    private PurchaseHandler purchaseHandler;

    public PurchaseRouter(PurchaseHandler purchaseHandler) {
        this.purchaseHandler = purchaseHandler;
    }

    @Bean
    public RouterFunction purchaseFunction() {
        return RouterFunctions.route(GET("timeline/purchases").and(accept(APPLICATION_JSON)), purchaseHandler::purchaseTimeLine);

    }
}
