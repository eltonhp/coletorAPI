package br.com.dito.coletaAPI.router;

import br.com.dito.coletaAPI.model.Ev;
import br.com.dito.coletaAPI.repository.EventRepository;
import br.com.dito.coletaAPI.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.ServerResponse.notFound;

@Service
public class CollectHandlerImpl implements CollectHandler {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventService eventService;


    @Override
    public Mono<ServerResponse> collect(ServerRequest request) {
        final Mono<Ev> evento = request.bodyToMono(Ev.class);
        return ServerResponse.ok().header("Access-Control-Allow-Origin", "*")
                     .contentType(MediaType.APPLICATION_STREAM_JSON)
                     .body(BodyInserters.fromPublisher(evento.flatMap(this.eventService::save), Ev.class))
                                 .switchIfEmpty(notFound().build());

    }

    @Override
    public Mono<ServerResponse> getEventByName(ServerRequest serverRequest) {
        return Mono.just(serverRequest.pathVariable("name")).map(this.eventService::validName)
                .onErrorMap(e -> new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage()))
                .flatMap(value -> ServerResponse.ok()
                                         .header("Access-Control-Allow-Origin", "*")
                                         .body(BodyInserters.fromPublisher(this.eventService.findEventByFilter(value), Ev.class)))
                .switchIfEmpty(notFound().build());
    }
}
