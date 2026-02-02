package com.charapadev.pdv.prices.exceptions;

import com.charapadev.pdv.base.exceptions.NotFoundException;

public class PriceTableNotFoundException extends NotFoundException {

    public PriceTableNotFoundException() {
        super("Tabela de preço não encontrada");
    }

}
