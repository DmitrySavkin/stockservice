package ru.savkin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.savkin.model.StockEntity;

@Repository
public interface StockRepositories extends JpaRepository<StockEntity, String> {
}
