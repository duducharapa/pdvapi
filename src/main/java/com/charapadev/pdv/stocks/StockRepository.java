package com.charapadev.pdv.stocks;

import com.charapadev.pdv.stocks.entities.Stock;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<@NonNull Stock,@NonNull Long> {

    @Modifying
    @Query("UPDATE Stock s SET s.active = false WHERE s.id = :stockId")
    void markAsInactive(Long stockId);

    @Query("SELECT s FROM Stock s WHERE name = :defaultStockName")
    Stock findDefault(String defaultStockName);

}
