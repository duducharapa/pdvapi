package com.charapadev.pdv.sales.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "Object containing the data to finish a sale")
public record AddSale(
        @Schema(
                requiredMode = Schema.RequiredMode.REQUIRED,
                description = "List of data to add items on sale"
        )
        List<AddItemSale> items,

        @Schema(
                requiredMode =  Schema.RequiredMode.REQUIRED,
                description = "List of data to add payments on sale"
        )
        List<AddPaymentMethod> payments,

        @Schema(description = "The identifier of price table, if wants to use one", example = "52")
        Long priceTableId
) {
}
