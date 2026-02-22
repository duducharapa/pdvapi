package com.charapadev.pdv.stocks.exceptions;

import com.charapadev.pdv.base.exceptions.NotFoundException;

public class StockNotFoundException extends NotFoundException {

    public StockNotFoundException() {
        super("Estoque não encontrado");
    }

}
