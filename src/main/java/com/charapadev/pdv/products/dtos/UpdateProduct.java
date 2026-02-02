package com.charapadev.pdv.products.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

@Schema(description = "Object containing the updatable data about a product instance")
public record UpdateProduct(
        @Schema(description = "An unique name that also identifies the product on application", example = "Hot chicken")
        String name,

        @Schema(description = "Default price for this product instance", example = "2.49")
        @PositiveOrZero
        BigDecimal price
) {
}
