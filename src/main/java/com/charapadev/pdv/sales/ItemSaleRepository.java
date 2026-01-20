package com.charapadev.pdv.sales;

import com.charapadev.pdv.sales.entities.ItemSale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemSaleRepository extends JpaRepository<ItemSale, Long> {
}
