package br.com.dito.coletaAPI.router;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Component
public class CollectRouter {

    private final CollectHandler collectHandler;



    public CollectRouter(CollectHandler collectHandler) {
        this.collectHandler = collectHandler;
    }

    @Bean
    public RouterFunction collectFunction() {
        return RouterFunctions.route(RequestPredicates.GET("event/{name}").and(accept(APPLICATION_JSON)), collectHandler::getEventByName)
                              .andRoute(RequestPredicates.POST("event").and(accept(APPLICATION_JSON)), collectHandler::collect);

    }
}