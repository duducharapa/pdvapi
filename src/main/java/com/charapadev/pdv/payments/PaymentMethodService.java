package com.charapadev.pdv.payments;

import com.charapadev.pdv.base.exceptions.NotFoundException;
import com.charapadev.pdv.payments.dtos.CreatePaymentMethod;
import com.charapadev.pdv.payments.dtos.UpdatePaymentMethod;
import com.charapadev.pdv.payments.entities.PaymentMethod;
import com.charapadev.pdv.payments.exceptions.PaymentNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentMethodService {

    private final PaymentMethodRepository paymentMethodRepository;

    public PaymentMethodService(PaymentMethodRepository paymentMethodRepository) {
        this.paymentMethodRepository = paymentMethodRepository;
    }


    public boolean existsById(Long id) {
        return paymentMethodRepository.existsById(id);
    }


    public List<PaymentMethod> findAll() {
        return paymentMethodRepository.findAll();
    }


    public PaymentMethod create(CreatePaymentMethod data) {
        PaymentMethod method = new PaymentMethod();
        method.setName(data.name());

        return paymentMethodRepository.save(method);
    }


    public PaymentMethod findById(Long id) throws NotFoundException {
        return paymentMethodRepository.findById(id).orElse(null);
    }


    public PaymentMethod find(String name) {
        return paymentMethodRepository.findByName(name);
    }


    public void update(Long id, UpdatePaymentMethod data) {
        PaymentMethod method = findById(id);
        if (method == null) throw new RuntimeException();

        if (data.name() != null) {
            method.setName(data.name());
        }

        paymentMethodRepository.save(method);
    }


    @Transactional
    public void delete(Long id) throws NotFoundException {
        boolean exists = existsById(id);

        if (!exists) throw new PaymentNotFoundException();

        paymentMethodRepository.markAsInactive(id);
    }

}
