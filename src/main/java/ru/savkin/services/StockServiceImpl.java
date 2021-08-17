package ru.savkin.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import ru.savkin.model.stocks.Stock;
import ru.savkin.repository.fakes.StockRepository;
import ru.savkin.stockoperator.StockLoader;
import ru.savkin.stockoperator.StockOperator;

import java.util.List;

@Service
public class StockServiceImpl implements StockService {


    private StockRepository stockRepository;

    @Value("${ref-data-url}")
    private String url;

    @Autowired
    private StockLoader stockLoader;

    @Autowired
    private StockOperator stockOperator;


    @Override
    public void process() {

        // for(;;) {

        List<Stock> stock = stockLoader.getStocksFromUrl(url);
        stockOperator.findActualHighStock(stock, 5);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //  }
    }
}
