package com.charapadev.pdv.products.dtos;

import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record UpdateProduct(
        String name,

        @PositiveOrZero
        BigDecimal price
) {
}
