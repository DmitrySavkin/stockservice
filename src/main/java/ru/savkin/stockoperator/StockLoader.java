package ru.savkin.stockoperator;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.savkin.model.stocks.IEXStock;
import ru.savkin.model.stocks.Stock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public abstract class StockLoader {


    public abstract List<Stock> getStocksFromUrl(String url);
}
