package ru.savkin.job;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.savkin.services.StockService;

@Component
@RequiredArgsConstructor
public class StockJob {

    private final StockService stockService;

    @Scheduled(fixedRate = 10000)
    public void process() {
        stockService.process();
    }
}
