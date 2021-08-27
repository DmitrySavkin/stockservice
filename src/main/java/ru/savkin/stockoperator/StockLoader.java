package ru.savkin.stockoperator;


import ru.savkin.model.stocks.Stock;
import java.util.List;


public abstract class StockLoader {


    public abstract List<Stock> getStocksFromUrl(String url);
}
