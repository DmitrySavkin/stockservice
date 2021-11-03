package ru.savkin.stockoperator;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import ru.savkin.dto.StockDto;
import ru.savkin.model.StockEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Component
@NoArgsConstructor
public class IEXStockLoader extends StockLoader {

    @Value("${ieaxapi.token}")
    private String token;


    @Override
    public List<StockDto> getStocksFromUrl(String url) {
        url += token;
        System.out.println(url);
        RestTemplate restTemplate = new RestTemplate();
        StockDto[] stockDTOs = restTemplate.getForObject(url, StockDto[].class);
        if(Objects.isNull(stockDTOs)) {
            stockDTOs = new StockDto[0];
        }
        List<StockDto> stockDtoList = new ArrayList<>();
        Collections.addAll(stockDtoList, stockDTOs);
        return stockDtoList;
    }

}
