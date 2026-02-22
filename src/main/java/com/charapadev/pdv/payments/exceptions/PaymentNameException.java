package com.charapadev.pdv.payments.exceptions;

import com.charapadev.pdv.base.exceptions.BusinessModelException;

public class PaymentNameException extends BusinessModelException {

    public PaymentNameException() {
        super("Already has a payment with the given name");
    }

}
