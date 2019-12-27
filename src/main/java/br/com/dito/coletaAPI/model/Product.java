package br.com.dito.coletaAPI.model;

import lombok.*;

import java.math.BigDecimal;

/**
 * @author Elton H. Paula
 */
@Data
@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private String name;
    private BigDecimal price;
}
