package ru.savkin.stockoperator;

import org.junit.jupiter.api.Test;
import ru.savkin.model.stocks.IEXStock;
import ru.savkin.model.stocks.Stock;
import ru.savkin.stockoperator.stockcontainers.StockContainer;
import ru.savkin.stockoperator.stockcontainers.StockContainerFabric;
import ru.savkin.stockoperator.stockcontainers.StockContainerType;


import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StockContainerTest {

    @Test
    public void testStockContainerImp() {
        StockContainer stockContainer = StockContainerFabric.getContainer(4);

        List<Stock> stockList = List.of(
                new IEXStock("A", new BigDecimal(3.4)),
                new IEXStock("B", new BigDecimal(222.4)),
                new IEXStock("C", new BigDecimal(1329.4)),
                new IEXStock("D", new BigDecimal(13.4)),
                new IEXStock("E", new BigDecimal(13.8)),
                new IEXStock("K", new BigDecimal(17.4)),
                new IEXStock("Z", new BigDecimal(0.4)));

        stockList.forEach(stock -> stockContainer.addStock(stock));
        List<Stock> result = stockContainer.getStocks();
        Stock maxStock = stockContainer.getMaxStock();
        Stock minStock = stockContainer.getMinStock();
        assertEquals(new BigDecimal(1329.4), maxStock.getPrice());
        assertEquals(new BigDecimal(13.8), minStock.getPrice());
        assertEquals(4, result.size());
    }

    @Test
    public void testStockContainerImpDescrement() {
        StockContainer stockContainer = StockContainerFabric.getContainer(4, StockContainerType.DESC);

        List<Stock> stockList = List.of(
                new IEXStock("A", new BigDecimal(3.4)),
                new IEXStock("B", new BigDecimal(222.4)),
                new IEXStock("C", new BigDecimal(1329.4)),
                new IEXStock("D", new BigDecimal(13.4)),
                new IEXStock("E", new BigDecimal(13.8)),
                new IEXStock("K", new BigDecimal(17.4)),
                new IEXStock("Z", new BigDecimal(0.4)));

        stockList.forEach(stock -> stockContainer.addStock(stock));
        List<Stock> result = stockContainer.getStocks();
        Stock maxStock = stockContainer.getMaxStock();
        Stock minStock = stockContainer.getMinStock();
        assertEquals(new BigDecimal(13.8), maxStock.getPrice());
        assertEquals(new BigDecimal(0.4), minStock.getPrice());
        assertEquals(4, result.size());
    }
}
