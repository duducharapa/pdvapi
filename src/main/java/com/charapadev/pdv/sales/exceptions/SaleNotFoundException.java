package com.charapadev.pdv.sales.exceptions;

public class SaleNotFoundException extends RuntimeException {

    public SaleNotFoundException() {
        super("Venda não encontrada");
    }

}
