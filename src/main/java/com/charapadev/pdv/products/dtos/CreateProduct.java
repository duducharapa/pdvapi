package com.charapadev.pdv.products.dtos;

import java.math.BigDecimal;

public record CreateProduct(
        String name,
        BigDecimal price
) {
}
