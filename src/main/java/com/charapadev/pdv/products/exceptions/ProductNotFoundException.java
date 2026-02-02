package com.charapadev.pdv.products.exceptions;

import com.charapadev.pdv.base.exceptions.NotFoundException;

public class ProductNotFoundException extends NotFoundException {

    public ProductNotFoundException() {
        super("Produto não encontrado");
    }

}
