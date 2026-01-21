package com.charapadev.pdv.payments;

import com.charapadev.pdv.base.exceptions.NotFoundException;
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

    private boolean existsById(Long id) {
        return paymentMethodRepository.existsById(id);
    }

    private PaymentMethod getOrThrow(Long id) throws NotFoundException {
        return paymentMethodRepository.findById(id).orElseThrow(NotFoundException::new);
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
        return getOrThrow(id);
    }

    public void update(Long id, UpdatePaymentMethod data) {
        PaymentMethod method = getOrThrow(id);

        if (data.name() != null) {
            method.setName(data.name());
        }

        paymentMethodRepository.save(method);
    }

    @Transactional
    public void delete(Long id) throws NotFoundException {
        boolean exists = existsById(id);

        if (!exists) throw new NotFoundException();

        paymentMethodRepository.markAsInactive(id);
    }

}
