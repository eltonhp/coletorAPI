package br.com.dito.coletaAPI.router;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * @author Elton H. Paula
 */
@Service
public interface PurchaseHandler {

    public Mono<ServerResponse> purchaseTimeLine(ServerRequest serverRequest);
}
