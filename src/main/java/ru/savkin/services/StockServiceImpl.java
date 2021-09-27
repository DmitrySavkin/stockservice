package ru.savkin.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import ru.savkin.model.stocks.Stock;
import ru.savkin.repository.StockRepositories;
import ru.savkin.stockoperator.StockLoader;
import ru.savkin.stockoperator.StockOperator;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class StockServiceImpl implements StockService {


//    private StockRepository stockRepository;
//
    @Value("${ref-data-url}")
    private String url;

    @Autowired
    private StockLoader stockLoader;

    @Autowired
    private StockOperator stockOperator;


    @Autowired
    private StockRepositories repository;

    @Override
    @Transactional
    public void process() {
        List<Stock> stock = stockLoader.getStocksFromUrl(url);
        stockOperator.findActualHighStock(stock, 5);
        try {
            Thread.sleep(5000);
            System.out.println("Stock  " + stock.size());
            saveStocks(stock.stream().limit(5).collect(Collectors.toList()));

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void saveStocks(List<Stock> stocks) {
        for (Stock stock : stocks) {
            if (repository.existsById(stock.getSymbol())) {
                Stock stockFromDB = repository.getById(stock.getSymbol());
                if (Objects.nonNull(stockFromDB)
                        && Objects.nonNull(stockFromDB.getPrice())
                        && Objects.nonNull(stock)
                        && Objects.nonNull(stock.getPrice())) {
                    if (stockFromDB.getPrice().compareTo(stock.getPrice()) != 0) {
                        repository.save(stock);
                    }
                }
            } else {
                repository.save(stock);
            }
        }
    }
}
