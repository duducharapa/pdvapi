package com.charapadev.pdv.products;

import com.charapadev.pdv.products.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
