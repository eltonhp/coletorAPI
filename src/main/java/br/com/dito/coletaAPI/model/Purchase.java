package br.com.dito.coletaAPI.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.core.annotation.Order;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Elton H. Paula
 */
@Data
@Getter @Setter
public class Purchase {
    private String timestamp;
    private BigDecimal revenue;
    @JsonProperty("transaction_id")
    private Long transactionId;
    @JsonProperty("store_name")
    private String storeName;
    private List<Product> products;
}