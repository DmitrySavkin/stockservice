package ru.savkin.stockoperator;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import ru.savkin.model.stocks.IEXStock;
import ru.savkin.model.stocks.Stock;
import ru.savkin.stockoperator.stockcontainers.StockContainer;
import ru.savkin.stockoperator.stockcontainers.StockContainerFabric;
import ru.savkin.stockoperator.stockcontainers.StockContainerType;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StockContainerTest {

    @Test
    public void testStockContainerImp() {
        StockContainer stockContainer = StockContainerFabric.getContainer(4);

        List<Stock> stockList = new ArrayList<>();
        stockList.add(new Stock("A", new BigDecimal(3.4)));
        stockList.add(new Stock("B", new BigDecimal(222.4)));
        stockList.add(new Stock("C", new BigDecimal(1329.4)));
        stockList.add(new Stock("D", new BigDecimal(13.4)));
        stockList.add(new Stock("E", new BigDecimal(13.8)));
        stockList.add(new Stock("K", new BigDecimal(17.4)));
        stockList.add(new Stock("Z", new BigDecimal(0.4)));
        stockList.forEach(stock -> stockContainer.addStock(stock));
        List<Stock> result = stockContainer.getStocks();
        Stock maxStock = stockContainer.getMaxStock();
        Stock minStock = stockContainer.getMinStock();
        assertEquals(new BigDecimal(1329.4), maxStock.getPrice());
        assertEquals(new BigDecimal(13.8), minStock.getPrice());
        assertEquals(4, result.size());
    }

//    @Ignore
////    @Test
////    public void testStockContainerImpDescrement() {
////        StockContainer stockContainer = StockContainerFabric.getContainer(4, StockContainerType.DESC);
////
////        List<Stock> stockList = new ArrayList<>();
////        stockList.add(new Stock("A", new BigDecimal(3.4)));
////        stockList.add(new Stock("B", new BigDecimal(222.4)));
////        stockList.add(new Stock("C", new BigDecimal(1329.4)));
////        stockList.add(new Stock("D", new BigDecimal(13.4)));
////        stockList.add(new Stock("E", new BigDecimal(13.8)));
////        stockList.add(new Stock("K", new BigDecimal(17.4)));
////        stockList.add(new Stock("Z", new BigDecimal(0.4)));
////        for (Stock stock: stockList) {
////            stockContainer.addStock(stock);
////        }
////        List<Stock> result = stockContainer.getStocks();
////        Stock maxStock = stockContainer.getMaxStock();
////        Stock minStock = stockContainer.getMinStock();
////        assertEquals(new BigDecimal(13.8), maxStock.getPrice());
////        assertEquals(new BigDecimal(0.4), minStock.getPrice());
////        assertEquals(4, result.size());
////    }
}
