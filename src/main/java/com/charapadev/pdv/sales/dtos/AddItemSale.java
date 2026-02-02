package com.charapadev.pdv.sales.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Object containing the data to add a product to be sold")
public record AddItemSale(
        @Schema(description = "The product identifier", example = "5")
        Long productId,

        @Schema(description = "The quantity of product", example = "2")
        Integer quantity
) {
}
