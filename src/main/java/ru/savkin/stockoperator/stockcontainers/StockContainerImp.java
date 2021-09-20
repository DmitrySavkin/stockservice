package ru.savkin.stockoperator.stockcontainers;

import org.apache.commons.lang3.tuple.Pair;
import ru.savkin.model.stocks.Stock;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class StockContainerImp extends StockContainer {

    volatile Pair<BigDecimal, Stock> minPair;
    volatile Pair<BigDecimal, Stock> maxPair;
    volatile TreeMap<BigDecimal, Stock> deque = new TreeMap();
    int limit;

    public StockContainerImp(int limit) {
        this.limit = limit;
    }


    @Override
    public Stock getMinStock() {
        return minPair.getValue();
    }

    @Override
    public Stock getMaxStock() {
        return maxPair.getValue();
    }

    @Override
    public List<Stock> getStocks() {
        return deque.values().stream().collect(Collectors.toList());
    }


    public void addStock(Stock stock) {
        if (minPair == null) {
            minPair = maxPair = Pair.of(stock.getPrice(), stock);
            deque.put(stock.getPrice(), stock);
        } else {
            if (deque.size() < limit) {
                if (maxPair.getKey().compareTo(stock.getPrice()) < 0) {
                    maxPair = Pair.of(stock.getPrice(), stock);
                }
                if (minPair.getKey().compareTo(stock.getPrice()) > 0) {
                    minPair = Pair.of(stock.getPrice(), stock);
                }
                deque.put(stock.getPrice(), stock);

            } else {
                addOrderedStock(stock);
            }
        }
    }

    //TODO REfactor
    protected void updateMinPair(Stock stock) {
        minPair = Pair.of(stock.getPrice(), stock);
        Pair<BigDecimal, Stock> tmp = maxPair;
        Map.Entry<BigDecimal, Stock> tmp3 = deque.higherEntry(tmp.getKey());
        maxPair = Pair.of(tmp3.getKey(), tmp3.getValue());
        deque.remove(tmp.getKey());
    }


    protected void addOrderedStock(Stock stock) {
        if (maxPair.getKey().compareTo(stock.getPrice()) < 0) {
            maxPair = Pair.of(stock.getPrice(), stock);
            Map.Entry<BigDecimal, Stock> tmp = minPair;
            Map.Entry<BigDecimal, Stock> tmp3 = deque.higherEntry(tmp.getKey());
            minPair = Pair.of(tmp3.getKey(), tmp3.getValue());
            deque.remove(tmp.getKey());
        }
        if (minPair.getKey().compareTo(stock.getPrice()) < 0
                && stock.getPrice().compareTo(maxPair.getKey()) < 0
        ) {
            Map.Entry<BigDecimal, Stock> tmp = minPair;
            deque.remove(tmp.getKey());
            deque.put(stock.getPrice(), stock);
            Map.Entry<BigDecimal, Stock> tmp3  = deque.firstEntry();
            minPair = Pair.of(tmp3.getKey(), tmp3.getValue());
        }
    }


    @Override
    public Iterator<Stock> iterator() {
        return deque.values().iterator();
    }

    @Override
    public void forEach(Consumer<? super Stock> action) {
        deque.values().forEach(action);
    }

    @Override
    public Spliterator<Stock> spliterator() {
        return deque.values().spliterator();
    }
}
