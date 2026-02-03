package com.charapadev.pdv.products;

import com.charapadev.pdv.products.entities.Product;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<@NonNull Product, @NonNull Long> {
}
