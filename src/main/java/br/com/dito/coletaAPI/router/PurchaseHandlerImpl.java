package br.com.dito.coletaAPI.router;

import br.com.dito.coletaAPI.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * @author Elton H. Paula
 */
@Service
public class PurchaseHandlerImpl implements  PurchaseHandler {

    @Autowired
    PurchaseService purchaseService;

    @Override
    public Mono<ServerResponse> purchaseTimeLine(ServerRequest serverRequest) {
        return ServerResponse.ok().body(purchaseService.purchaseTimeLine(), Object.class);
    }
}
