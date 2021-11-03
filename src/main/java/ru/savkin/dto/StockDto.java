package ru.savkin.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.savkin.model.StockEntity;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockDto {
    @JsonProperty("symbol")
    private String symbol;
    @JsonProperty("exchange")
    private String exchange;
    @JsonProperty("exchangeSuffix")
    private String exchangeSuffix;
    @JsonProperty("exchangeName")
    private String exchangeName;
    @JsonProperty("name")
    private String name;
    @JsonProperty("date")
    private String date;
    @JsonProperty("type")
    private String type;
    @JsonProperty("iexId")
    private String iexId;
    @JsonProperty("region")
    private String region;
    @JsonProperty("currency")
    private String currency;
    @JsonProperty("isEnable")
    private boolean isEnable;
    @JsonProperty("figi")
    private String figi;
    @JsonProperty("cik")
    private String cik;
    @JsonProperty("lei")
    private String lei;
    @JsonProperty("price")
    private BigDecimal price;

    public StockEntity toStockEntity() {
        return new StockEntity(this.symbol, this.price);
    }
}
