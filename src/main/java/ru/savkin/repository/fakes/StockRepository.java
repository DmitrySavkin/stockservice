package ru.savkin.repository.fakes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.savkin.model.stocks.Stock;

public interface StockRepository extends JpaRepository<Stock, String> {


   // @Query("INSERT INTO Stock VALUE()")
    //void saveUniqueStocks(Stock st);


}