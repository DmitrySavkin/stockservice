package ru.savkin.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Entity
public class StockEntity implements Serializable {


    @Id
    private String symbol;
    private BigDecimal price;


    public StockEntity(String symbol, BigDecimal price) {
        this.symbol = symbol;
        this.price = price;
    }
}
