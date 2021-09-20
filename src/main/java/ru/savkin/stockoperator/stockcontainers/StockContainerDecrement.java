package ru.savkin.stockoperator.stockcontainers;

import org.apache.commons.lang3.tuple.Pair;
import ru.savkin.model.stocks.Stock;

import java.math.BigDecimal;
import java.util.Map;

public class StockContainerDecrement extends StockContainerImp {

    public StockContainerDecrement(int limit) {
        super(limit);
    }


    @Override
    protected void addOrderedStock(Stock stock) {

        if (minPair.getKey().compareTo(stock.getPrice()) > 0) {
            updateMinPair(stock);
        }
        if (maxPair.getKey().compareTo(stock.getPrice()) > 0
                && stock.getPrice().compareTo(minPair.getKey()) > 0
        ) {
            Map.Entry<BigDecimal, Stock> tmp = maxPair;
            deque.remove(tmp.getKey());
            deque.put(stock.getPrice(), stock);
            Map.Entry<BigDecimal, Stock> tmp3 = deque.lastEntry();
            maxPair = Pair.of(tmp3.getKey(), tmp3.getValue());
        }
    }
}

