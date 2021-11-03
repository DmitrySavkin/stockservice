package ru.savkin.stockoperator.stockcontainers;

import ru.savkin.model.StockEntity;

import java.util.List;

public abstract class StockContainer implements Iterable<StockEntity> {


    public abstract void addStock(StockEntity stockEntity);

    public abstract StockEntity getMinStock();


    public abstract StockEntity getMaxStock();

    public abstract List<StockEntity> getStocks();
}
