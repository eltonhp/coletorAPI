package br.com.dito.coletaAPI.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Elton H. Paula
 */
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = { "id" })
public class Ev {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Getter @Setter
    private String event;
    @Getter @Setter
    private String timestamp;
}
