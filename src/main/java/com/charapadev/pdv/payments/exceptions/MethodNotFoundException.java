package com.charapadev.pdv.payments.exceptions;

import com.charapadev.pdv.base.exceptions.NotFoundException;

public class MethodNotFoundException extends NotFoundException {

    public MethodNotFoundException() {
        super("Método de pagamento não encontrado");
    }

}
