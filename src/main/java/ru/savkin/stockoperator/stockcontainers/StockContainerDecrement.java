package ru.savkin.stockoperator.stockcontainers;

import org.apache.commons.lang3.tuple.Pair;
import ru.savkin.model.StockEntity;

import java.math.BigDecimal;
import java.util.Map;

@Deprecated
public class StockContainerDecrement extends StockContainerImp {

    public StockContainerDecrement(int limit) {
        super(limit);
    }


    @Override
    protected void addOrderedStock(StockEntity stockEntity) {

        if (minPair.getKey().compareTo(stockEntity.getPrice()) > 0) {
            updateMinPair(stockEntity);
        }
        if (maxPair.getKey().compareTo(stockEntity.getPrice()) > 0
                && stockEntity.getPrice().compareTo(minPair.getKey()) > 0
        ) {
            Map.Entry<BigDecimal, StockEntity> tmp = maxPair;
            deque.remove(tmp.getKey());
            deque.put(stockEntity.getPrice(), stockEntity);
            Map.Entry<BigDecimal, StockEntity> tmp3 = deque.lastEntry();
            maxPair = Pair.of(tmp3.getKey(), tmp3.getValue());
        }
    }
}

