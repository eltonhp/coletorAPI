package br.com.dito.coletaAPI.router;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Service
public interface CollectHandler {

    public Mono<ServerResponse> collect(ServerRequest request);

    public Mono<ServerResponse> getEventByName(ServerRequest serverRequest);
}
