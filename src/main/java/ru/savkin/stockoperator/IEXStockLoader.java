package ru.savkin.stockoperator;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import ru.savkin.model.stocks.Stock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@NoArgsConstructor
public class IEXStockLoader extends StockLoader {

    @Value("${token}")
    private String token;


    @Override
    public List<Stock> getStocksFromUrl(String url) {

        url += token;
        System.out.println(url);
        RestTemplate restTemplate = new RestTemplate();
        Stock[] stocks = restTemplate.getForObject(url, Stock[].class);
        List<Stock> stockList = new ArrayList<>();
        Collections.addAll(stockList, stocks);
        return stockList;
    }

}
