package com.charapadev.pdv.prices;

import com.charapadev.pdv.prices.entities.PriceItem;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceItemRepository extends JpaRepository<@NonNull PriceItem, @NonNull Long> {

    @Query("SELECT pi FROM PriceItem pi LEFT JOIN FETCH pi.product p LEFT JOIN FETCH pi.priceTable pt WHERE p.id = :productId AND pt.id = :tableId OR pt.name = :defaultTableName")
    PriceItem findByTableAndProduct(Long productId, Long tableId, String defaultTableName);

}
