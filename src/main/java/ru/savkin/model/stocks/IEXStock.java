package ru.savkin.model.stocks;


import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@NoArgsConstructor
public class IEXStock extends Stock {


    public IEXStock(String symbol, BigDecimal price) {
        super(symbol, price);
    }
}
