package com.charapadev.pdv.stocks;

import com.charapadev.pdv.stocks.dtos.CreateStock;
import com.charapadev.pdv.stocks.dtos.UpdateStock;
import com.charapadev.pdv.stocks.entities.Stock;
import com.charapadev.pdv.stocks.exceptions.StockNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Stocks", description = "The place where the products can be stored and calculated after operations")
@RestController
@RequestMapping("/stocks")
public class StockController {

    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }


    @Operation(
            summary = "List stocks",
            description = "List all stocks registered on application"
    )
    @ApiResponse(
            description = "Stocks found successfully",
            responseCode = "200",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    array = @ArraySchema(schema = @Schema(implementation = Stock.class))
            )
    )
    @GetMapping
    public List<Stock> findAll() {
        return stockService.findAll();
    }


    @Operation(
            summary = "Create stock",
            description = "Generates a new stock if does not exists with the same name"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201", description = "Stock created successfully",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Stock.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "422", description = "Stock with the given name already registered",
                    content = @Content()
            )
    })
    @PostMapping
    public Stock create(@RequestBody CreateStock data) {
        return stockService.create(data);
    }


    @Operation(
            summary = "Search stock",
            description = "Searches for a stock and returns it if found",
            parameters = {
                    @Parameter(name = "id", description = "Sale ID", in = ParameterIn.PATH)
            }
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200", description = "Stock found successfully",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Stock.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404", description = "Stock not found",
                    content = @Content()
            )
    })
    @GetMapping("/{id}")
    public Stock findById(@PathVariable Long id) {
        Stock stockFound = stockService.findById(id);

        if (stockFound == null) throw new StockNotFoundException();

        return stockFound;
    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody UpdateStock data) throws StockNotFoundException {
        Stock stockFound = stockService.findById(id);

        if (stockFound == null) throw new StockNotFoundException();

        stockService.update(stockFound, data);
    }


    @Operation(
            summary = "Delete stock",
            description = "Deactivate an specific stock on application",
            parameters = {
                    @Parameter(name = "id", description = "Stock ID", in = ParameterIn.PATH)
            }
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204", description = "Stock deactivated successfully",
                    content = @Content()
            ),
            @ApiResponse(
                    responseCode = "404", description = "Stock not found",
                    content = @Content()
            )
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        boolean exists = stockService.exists(id);

        if (!exists) throw new StockNotFoundException();

        stockService.delete(id);
    }

}
