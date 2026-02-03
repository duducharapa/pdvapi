package com.charapadev.pdv.sales;

import com.charapadev.pdv.sales.entities.Sale;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SaleRepository extends JpaRepository<@NonNull Sale, @NonNull Long> {

    @Query("SELECT DISTINCT s FROM Sale s LEFT JOIN FETCH s.itemSales i")
    List<Sale> findAllWithItems();

    @Query("SELECT DISTINCT s FROM Sale s LEFT JOIN FETCH s.itemSales i WHERE s.id = :id")
    Optional<Sale> findByIdWithItems(Long id);

    @Modifying
    @Query("UPDATE Sale s SET s.visible = false WHERE s.id = :id")
    void markAsInactive(Long id);

}
