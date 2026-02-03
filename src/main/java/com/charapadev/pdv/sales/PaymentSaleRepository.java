package com.charapadev.pdv.sales;

import com.charapadev.pdv.sales.entities.PaymentSale;
import org.jspecify.annotations.NonNull;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentSaleRepository extends CrudRepository<@NonNull PaymentSale, @NonNull Long> {
}
