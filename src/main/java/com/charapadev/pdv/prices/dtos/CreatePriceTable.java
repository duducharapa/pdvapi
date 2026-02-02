package com.charapadev.pdv.prices.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Object containing the data to create a new price table")
public record CreatePriceTable(
        @Schema(
                description = "An short text identifier for each table",
                example = "John's shop",
                requiredMode =  Schema.RequiredMode.REQUIRED
        )
        @NotBlank
        String name,

        @Schema(description = "A short text about the purpose of this table instance", example = "Default system table")
        String description
) {
}
