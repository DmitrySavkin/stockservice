package ru.savkin.repository;

import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.savkin.model.stocks.Stock;

@Repository
public interface StockRepositories extends JpaRepository<Stock, String> {
}
