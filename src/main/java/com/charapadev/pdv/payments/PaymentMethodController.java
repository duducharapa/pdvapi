package com.charapadev.pdv.payments;

import com.charapadev.pdv.payments.dtos.CreatePaymentMethod;
import com.charapadev.pdv.payments.dtos.UpdatePaymentMethod;
import com.charapadev.pdv.payments.entities.PaymentMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payment-methods")
public class PaymentMethodController {

    private final PaymentMethodService paymentMethodService;

    public PaymentMethodController(PaymentMethodService paymentMethodService) {
        this.paymentMethodService = paymentMethodService;
    }

    @GetMapping
    public List<PaymentMethod> findAll() {
        return paymentMethodService.findAll();
    }

    @PostMapping
    public PaymentMethod create(@RequestBody CreatePaymentMethod data) {
        return paymentMethodService.create(data);
    }

    @GetMapping("/{id}")
    public PaymentMethod search(@PathVariable Long id) {
        return paymentMethodService.findById(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody UpdatePaymentMethod data) {
        paymentMethodService.update(id, data);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        paymentMethodService.delete(id);
    }

}
