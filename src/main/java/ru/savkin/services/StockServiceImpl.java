package ru.savkin.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import ru.savkin.dto.StockDto;
import ru.savkin.model.StockEntity;
import ru.savkin.repository.StockRepositories;
import ru.savkin.stockoperator.StockLoader;
import ru.savkin.stockoperator.StockOperator;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {

    @Value("${ieaxapi.ref-data-url}")
    private String url;

    private final StockLoader stockLoader;
    private final StockOperator stockOperator;
    private final StockRepositories repository;

    @Autowired
    private MessageSender messageSender;


    @Override
    @Transactional
    public void process() {
        List<StockDto> stockDTOs = stockLoader.getStocksFromUrl(url);
        List<StockEntity> stockEntities = stockDTOs.stream().map(StockDto::toStockEntity).collect(Collectors.toList());
        stockOperator.findActualHighStock(stockEntities, 5);
        log.info("Stock  {}", stockDTOs.size());
        messageSender.send("New stock  : Time  " + LocalDateTime.now());
        saveStocks(stockEntities.stream().limit(5).collect(Collectors.toList()));

    }

    private void saveStocks(List<StockEntity> stockEntities) {
        stockEntities.forEach(stock -> {
            if (repository.existsById(stock.getSymbol())) {
                StockEntity stockEntityFromDB = repository.getById(stock.getSymbol());
                if (Objects.nonNull(stockEntityFromDB.getPrice()) && Objects.nonNull(stock.getPrice())) {
                    if (stockEntityFromDB.getPrice().compareTo(stock.getPrice()) != 0) {
                        repository.save(stock);
                    }
                }
            } else {
                repository.save(stock);
            }
        });
    }
}
