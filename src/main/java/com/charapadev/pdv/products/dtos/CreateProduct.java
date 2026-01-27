package com.charapadev.pdv.products.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record CreateProduct(
        @NotBlank
        String name,

        @PositiveOrZero
        BigDecimal price
) {
}
