package ru.savkin.stockoperator.stockcontainers;

import org.apache.commons.lang3.tuple.Pair;
import ru.savkin.model.StockEntity;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class StockContainerImp extends StockContainer {

    volatile Pair<BigDecimal, StockEntity> minPair;
    volatile Pair<BigDecimal, StockEntity> maxPair;
    volatile TreeMap<BigDecimal, StockEntity> deque = new TreeMap();
    int limit;

    public StockContainerImp(int limit) {
        this.limit = limit;
    }


    @Override
    public StockEntity getMinStock() {
        return minPair.getValue();
    }

    @Override
    public StockEntity getMaxStock() {
        return maxPair.getValue();
    }

    @Override
    public List<StockEntity> getStocks() {
        return deque.values().stream().collect(Collectors.toList());
    }


    public void addStock(StockEntity stockEntity) {
        if (minPair == null) {
            minPair = maxPair = Pair.of(stockEntity.getPrice(), stockEntity);
            deque.put(stockEntity.getPrice(), stockEntity);
        } else {
            if (deque.size() < limit) {
                if (maxPair.getKey().compareTo(stockEntity.getPrice()) < 0) {
                    maxPair = Pair.of(stockEntity.getPrice(), stockEntity);
                }
                if (minPair.getKey().compareTo(stockEntity.getPrice()) > 0) {
                    minPair = Pair.of(stockEntity.getPrice(), stockEntity);
                }
                deque.put(stockEntity.getPrice(), stockEntity);

            } else {
                addOrderedStock(stockEntity);
            }
        }
    }

    //TODO REfactor
    protected void updateMinPair(StockEntity stockEntity) {
        minPair = Pair.of(stockEntity.getPrice(), stockEntity);
        Pair<BigDecimal, StockEntity> tmp = maxPair;
        Map.Entry<BigDecimal, StockEntity> tmp3 = deque.higherEntry(tmp.getKey());
        maxPair = Pair.of(tmp3.getKey(), tmp3.getValue());
        deque.remove(tmp.getKey());
    }


    protected void addOrderedStock(StockEntity stockEntity) {
        if (maxPair.getKey().compareTo(stockEntity.getPrice()) < 0) {
            maxPair = Pair.of(stockEntity.getPrice(), stockEntity);
            Map.Entry<BigDecimal, StockEntity> tmp = minPair;
            Map.Entry<BigDecimal, StockEntity> tmp3 = deque.higherEntry(tmp.getKey());
            minPair = Pair.of(tmp3.getKey(), tmp3.getValue());
            deque.remove(tmp.getKey());
        }
        if (minPair.getKey().compareTo(stockEntity.getPrice()) < 0
                && stockEntity.getPrice().compareTo(maxPair.getKey()) < 0
        ) {
            Map.Entry<BigDecimal, StockEntity> tmp = minPair;
            deque.remove(tmp.getKey());
            deque.put(stockEntity.getPrice(), stockEntity);
            Map.Entry<BigDecimal, StockEntity> tmp3  = deque.firstEntry();
            minPair = Pair.of(tmp3.getKey(), tmp3.getValue());
        }
    }


    @Override
    public Iterator<StockEntity> iterator() {
        return deque.values().iterator();
    }

    @Override
    public void forEach(Consumer<? super StockEntity> action) {
        deque.values().forEach(action);
    }

    @Override
    public Spliterator<StockEntity> spliterator() {
        return deque.values().spliterator();
    }
}
