package ru.savkin.model.stocks;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.id.IdentityGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
//@JsonIgnoreProperties(ignoreUnknown=true)
public class Stock implements Serializable {


    /*
     "symbol": "AAC+",
        "exchange": "NYS",
        "exchangeSuffix": "UN",
        "exchangeName": " IgckoaehnE crcoYwkSneNt  x",
        "name": " )r/(9-1aeoi0 t9 Ai0sop1 aWuarioircq9rtt9o/ nsnnrsAC",
        "date": "2021-08-07",
        "type": "wt",
        "iexId": null,
        "region": "US",
        "currency": "USD",
        "isEnabled": true,
        "figi": "LBB0GGZ1NL0N",
        "cik": "1915967",
        "lei": null
     */


   // @GeneratedValue(strategy = GenerationType.IDENTITY)
   // private int id;
    @Id
    private String symbol;

    private String exchange;

    private String exchangeSuffix;

    private String exchangeName;

    private String name;

    //TODO LocalDate
    private String date;

    private String type;

    private String iexId;

    private String region;

    private String currency;

    private boolean isEnable;

    private String figi;

    private String cik;

    private String lei;

    private BigDecimal price;


    public Stock(String symbol, BigDecimal price) {
        this.symbol = symbol;
        this.price = price;
    }
}
