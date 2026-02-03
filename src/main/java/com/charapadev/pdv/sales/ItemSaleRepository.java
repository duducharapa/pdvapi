package com.charapadev.pdv.sales;

import com.charapadev.pdv.sales.entities.ItemSale;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ItemSaleRepository extends JpaRepository<@NonNull ItemSale, @NonNull Long> {

    @Query("SELECT count(i) FROM ItemSale i FETCH JOIN i.product p WHERE p.id = :productId")
    Integer countByProduct(Long productId);

}
