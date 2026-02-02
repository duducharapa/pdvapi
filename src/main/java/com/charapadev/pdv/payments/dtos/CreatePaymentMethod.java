package com.charapadev.pdv.payments.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Object containing the data to create a new payment method")
public record CreatePaymentMethod(
        @Schema(
                description = "Unique name used to identify the instance on reports and sales closing",
                example = "Crédito de loja",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        String name
) {
}
