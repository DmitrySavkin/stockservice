package ru.savkin.stockoperator;


import ru.savkin.dto.StockDto;
import ru.savkin.model.StockEntity;

import java.util.List;


public abstract class StockLoader {


    public abstract List<StockDto> getStocksFromUrl(String url);
}
