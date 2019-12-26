package br.com.dito.coletaAPI.model;

import lombok.*;

import java.math.BigDecimal;

@Data
@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private String name;
    private BigDecimal price;
}
