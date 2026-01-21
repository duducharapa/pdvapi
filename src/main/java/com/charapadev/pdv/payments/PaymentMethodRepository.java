package com.charapadev.pdv.payments;

import com.charapadev.pdv.payments.entities.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {

    @Modifying
    @Query("UPDATE PaymentMethod p SET p.active = false WHERE p.id = :id")
    void markAsInactive(Long id);

}
