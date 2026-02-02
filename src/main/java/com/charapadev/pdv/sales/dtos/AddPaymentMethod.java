package com.charapadev.pdv.sales.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(description = "Object containing the data to refer an payment method on sale")
public record AddPaymentMethod(
        @Schema(description = "Payment method identifier", example = "4")
        Long methodId,

        @Schema(description = "The amount of money paid using this method", example = "34.89")
        BigDecimal amount
) {
}
