package com.charapadev.pdv.prices;

import com.charapadev.pdv.prices.dtos.CreatePriceTable;
import com.charapadev.pdv.prices.entities.PriceTable;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Price tables", description = "Represents the groupment of product prices")
@RestController
@RequestMapping("/price-tables")
public class PriceTableController {

    private final PriceTableService priceTableService;

    public PriceTableController(PriceTableService priceTableService) {
        this.priceTableService = priceTableService;
    }

    @Operation(
            summary = "List price tables",
            description = "Lists all price tables found on application"
    )
    @ApiResponse(
            description = "Tables found successfully", responseCode = "200",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    array = @ArraySchema(schema = @Schema(implementation = PriceTable.class))
            )
    )
    @GetMapping
    public List<PriceTable> list() {
        return priceTableService.findAll();
    }

    @Operation(
            summary = "Create price table",
            description = "Generates a new price table if does not exists any table with the same name"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201", description = "Price table created successfully",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = PriceTable.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "422", description = "Price table with the given name already registered",
                    content = @Content()
            )
    })
    @PostMapping
    public PriceTable create(@RequestBody CreatePriceTable data) {
        return priceTableService.create(data);
    }

}
