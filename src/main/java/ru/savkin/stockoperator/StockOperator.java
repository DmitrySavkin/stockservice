package ru.savkin.stockoperator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.savkin.model.stocks.Stock;
import ru.savkin.stockoperator.stockcontainers.StockContainer;
import ru.savkin.stockoperator.stockcontainers.StockContainerFabric;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;


@Component
public class StockOperator {


    @Value("${latest-price-url}")
    private String latestPriceUrl;

    @Value("${token}")
    private String token;

    private StockContainer stockContainer;

    public void findActualHighStock(List<Stock> stocksList, int limit) {

        stockContainer = StockContainerFabric.getContainer(limit);

        if (Objects.isNull(stocksList)) {
            //TODO Loggg
        } else {
            List<Callable<Object>> calls = stocksList.stream().limit(20).map(stock ->
                    Executors.callable(new StockParser(stock))).collect(Collectors.toList());
            ExecutorService executorService = Executors.newFixedThreadPool(64);
            try {
                executorService.invokeAll(calls);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        stockContainer.forEach(System.out::println);

    }

    private class StockParser implements Runnable {

        private Stock stock;


        public StockParser(Stock stock) {
            this.stock = stock;

        }

        @Override
        public void run() {

            String requestUrl = String.format(latestPriceUrl + token, stock.getSymbol());
            RestTemplate restTemplate = new RestTemplate();
            BigDecimal price = restTemplate.getForObject(requestUrl, BigDecimal.class);
            stock.setPrice(price);
            stockContainer.addStock(stock);
        }

    }


}
