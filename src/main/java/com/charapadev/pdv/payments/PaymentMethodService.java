package com.charapadev.pdv.payments;

import com.charapadev.pdv.payments.dtos.CreatePaymentMethod;
import com.charapadev.pdv.payments.dtos.UpdatePaymentMethod;
import com.charapadev.pdv.payments.entities.PaymentMethod;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentMethodService {

    private final PaymentMethodRepository paymentMethodRepository;

    public PaymentMethodService(PaymentMethodRepository paymentMethodRepository) {
        this.paymentMethodRepository = paymentMethodRepository;
    }

    // Check if a method exists using the given ID
    public boolean existsById(Long id) {
        return paymentMethodRepository.existsById(id);
    }


    // List all payment methods found
    public List<PaymentMethod> findAll() {
        return paymentMethodRepository.findAll();
    }


    // Generates a new payment method
    public PaymentMethod create(CreatePaymentMethod data) {
        PaymentMethod method = new PaymentMethod();
        method.setName(data.name());

        return paymentMethodRepository.save(method);
    }


    // Searches a method and returns it or a null reference
    public PaymentMethod find(Long id) {
        return paymentMethodRepository.findById(id).orElse(null);
    }


    // Searches a method using the name as discriminator
    public PaymentMethod find(String name) {
        return paymentMethodRepository.findByName(name);
    }


    // Updates the data about a method
    public void update(PaymentMethod method, UpdatePaymentMethod data) {
        if (data.name() != null) {
            method.setName(data.name());
        }

        paymentMethodRepository.save(method);
    }


    // Makes a soft delete on payment method found
    @Transactional
    public void delete(Long id) {
        paymentMethodRepository.markAsInactive(id);
    }

}
