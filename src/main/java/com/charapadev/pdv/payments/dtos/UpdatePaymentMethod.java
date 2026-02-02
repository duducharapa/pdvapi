package com.charapadev.pdv.payments.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Object containing the data to modify on specific payment method")
public record UpdatePaymentMethod(
        @Schema(
                description = "Unique name used to identify the instance on reports and sales closing",
                example = "Crédito de loja"
        )
        String name
) {
}
