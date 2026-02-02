package com.charapadev.pdv.payments.exceptions;

import com.charapadev.pdv.base.exceptions.NotFoundException;

public class PaymentNotFoundException extends NotFoundException {

    public PaymentNotFoundException() {
        super("Método de pagamento não encontrado");
    }

}
