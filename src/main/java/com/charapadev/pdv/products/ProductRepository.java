package com.charapadev.pdv.products;

import com.charapadev.pdv.products.entities.Product;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<@NonNull Product, @NonNull Long> {

    @Modifying
    @Query("UPDATE Product p SET p.active = false WHERE p.id = :productId")
    void markAsInactive(Long productId);

}
