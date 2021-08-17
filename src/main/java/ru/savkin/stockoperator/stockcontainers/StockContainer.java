package ru.savkin.stockoperator.stockcontainers;

import ru.savkin.model.stocks.Stock;

import java.util.List;

public abstract class StockContainer implements Iterable<Stock> {


    public abstract void addStock(Stock stock);

    public abstract Stock getMinStock();


    public abstract Stock getMaxStock();

    public abstract List<Stock> getStocks();
}
