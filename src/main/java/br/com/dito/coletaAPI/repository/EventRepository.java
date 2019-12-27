package br.com.dito.coletaAPI.repository;

import br.com.dito.coletaAPI.model.Ev;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Elton H. Paula
 */
@Repository
public interface EventRepository extends CrudRepository<Ev, Integer> {
    @Query("select ev from Ev ev where UPPER(ev.event) LIKE :eventName%")
    public Iterable<Ev> findEventByFilter(@Param("eventName") String eventName);
}