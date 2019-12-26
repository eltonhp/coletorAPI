package br.com.dito.coletaAPI.service;

import br.com.dito.coletaAPI.exception.EventNotFoundException;
import br.com.dito.coletaAPI.model.Ev;
import br.com.dito.coletaAPI.repository.EventRepository;
import br.com.dito.coletaAPI.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EventService {

    @Autowired
    public EventRepository eventRepository;

    @Autowired
    private Util util;

    public Flux<Ev> findAll() {
        return Flux.fromIterable(this.eventRepository.findAll());
    }

    public Flux<Ev> findEventByFilter(String event) {
        return Flux.fromIterable(this.eventRepository.findEventByFilter(event.toUpperCase()));
    }

    public String validName(String eventName) {
        if(eventName == null) {
            throw new EventNotFoundException("Event name not found");
        }

        if(eventName.length() < 2) {
            throw new EventNotFoundException("amount of character of the event name less than 2");
        }

        if(!util.isLetter(eventName))  {
            throw new EventNotFoundException("not found letter");
        }

        return eventName;
    }

    public Mono<Ev> save(Ev ev) {
        if(ev.getEvent() == null) {
            throw new EventNotFoundException("Event not found");
        }

        if(ev.getTimestamp() == null) {
            throw new EventNotFoundException("timestamp not found");
        }

        return Mono.just(eventRepository.save(ev));
    }
}
