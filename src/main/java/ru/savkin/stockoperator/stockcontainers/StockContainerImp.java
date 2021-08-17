package ru.savkin.stockoperator.stockcontainers;

import ru.savkin.model.stocks.Stock;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class StockContainerImp extends StockContainer {

    volatile Map.Entry<BigDecimal, Stock> minPair;
    volatile Map.Entry<BigDecimal, Stock> maxPair;
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
            minPair = maxPair = Map.entry(stock.getPrice(), stock);
            deque.put(stock.getPrice(), stock);
        } else {
            if (deque.size() < limit) {
                if (maxPair.getKey().compareTo(stock.getPrice()) < 0) {
                    maxPair = Map.entry(stock.getPrice(), stock);
                }
                if (minPair.getKey().compareTo(stock.getPrice()) > 0) {
                    minPair = Map.entry(stock.getPrice(), stock);
                }
                deque.put(stock.getPrice(), stock);

            } else {
                addOrderedStock(stock);
            }
        }
    }

    //TODO REfactor
    protected void updateMinPair(Stock stock) {
        minPair = Map.entry(stock.getPrice(), stock);
        Map.Entry<BigDecimal, Stock> tmp = maxPair;
        maxPair = deque.higherEntry(tmp.getKey());
        deque.remove(tmp.getKey());
    }


    protected void addOrderedStock(Stock stock) {
        if (maxPair.getKey().compareTo(stock.getPrice()) < 0) {
            maxPair = Map.entry(stock.getPrice(), stock);
            Map.Entry<BigDecimal, Stock> tmp = minPair;
            minPair = deque.higherEntry(tmp.getKey());
            deque.remove(tmp.getKey());
        }
        if (minPair.getKey().compareTo(stock.getPrice()) < 0
                && stock.getPrice().compareTo(maxPair.getKey()) < 0
        ) {
            Map.Entry<BigDecimal, Stock> tmp = minPair;
            deque.remove(tmp.getKey());
            deque.put(stock.getPrice(), stock);
            minPair = deque.firstEntry();
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
