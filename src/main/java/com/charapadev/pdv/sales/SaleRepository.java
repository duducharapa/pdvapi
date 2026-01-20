package com.charapadev.pdv.sales;

import com.charapadev.pdv.sales.entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query("SELECT DISTINCT s FROM Sale s LEFT JOIN FETCH s.itemSales i")
    List<Sale> findAllWithItems();

    @Query("SELECT DISTINCT s FROM Sale s LEFT JOIN FETCH s.itemSales i WHERE s.id = :id")
    Optional<Sale> findByIdWithItems(Long id);

}
