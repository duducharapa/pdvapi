package com.charapadev.pdv.prices;

import com.charapadev.pdv.prices.entities.PriceTable;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceTableRepository extends JpaRepository<@NonNull PriceTable, @NonNull Long> {

    @Query("SELECT p FROM PriceTable p WHERE name = :defaultTableName")
    PriceTable findDefault(String defaultTableName);

}
