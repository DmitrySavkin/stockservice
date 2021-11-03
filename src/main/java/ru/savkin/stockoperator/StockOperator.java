package ru.savkin.stockoperator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.savkin.model.StockEntity;
import ru.savkin.stockoperator.stockcontainers.StockContainer;
import ru.savkin.stockoperator.stockcontainers.StockContainerFabric;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;


@Component
public class StockOperator {


    @Value("${ieaxapi.latest-price-url}")
    private String latestPriceUrl;

    @Value("${ieaxapi.token}")
    private String token;

    private StockContainer stockContainer;

    public void findActualHighStock(List<StockEntity> stocksList, int limit) {

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

        private StockEntity stockEntity;


        public StockParser(StockEntity stockEntity) {
            this.stockEntity = stockEntity;

        }

        @Override
        public void run() {

            String requestUrl = String.format(latestPriceUrl + token, stockEntity.getSymbol());
            RestTemplate restTemplate = new RestTemplate();
            BigDecimal price = restTemplate.getForObject(requestUrl, BigDecimal.class);
            stockEntity.setPrice(price);
            stockContainer.addStock(stockEntity);
        }

    }


}
