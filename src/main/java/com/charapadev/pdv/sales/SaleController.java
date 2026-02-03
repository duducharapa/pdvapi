package com.charapadev.pdv.sales;

import com.charapadev.pdv.sales.dtos.AddSale;
import com.charapadev.pdv.sales.entities.Sale;
import com.charapadev.pdv.sales.exceptions.SaleNotFoundException;
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

@Tag(name = "Sales", description = "The process about sell products")
@RestController
@RequestMapping("/sales")
public class SaleController {

    private final SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }


    @Operation(
            summary = "List sales",
            description = "Lists all sales registered on application"
    )
    @ApiResponse(
            description = "Sales found successfully", responseCode = "200",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    array = @ArraySchema(schema = @Schema(implementation = Sale.class))
            )
    )
    @GetMapping
    public List<Sale> list() {
        return saleService.findAll();
    }


    @Operation(
            summary = "Finish sale",
            description = "Finishes a sale and returns if the operation was concluded or not"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201", description = "Sale finished successfully",
                    content = @Content()
            )
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void add(@RequestBody AddSale data) {
        saleService.create(data);
    }


    @Operation(
            summary = "Search sale",
            description = "Searches for a sale using the given identifier and returns it if found",
            parameters = {
                    @Parameter(name = "id", description = "Sale ID", in = ParameterIn.PATH)
            }
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Sale found successfully",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Sale.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404", description = "Sale not found",
                    content = @Content()
            )
    })
    @GetMapping("/{id}")
    public Sale search(@PathVariable Long id) {
        return saleService.findById(id);
    }

    // TODO: Alterar método para inativar a venda ao invés de excluir
    @Operation(
            summary = "Remove sale",
            description = "Marks the given sale identified by ID as inactive",
            parameters = {
                    @Parameter(name = "id", description = "Sale ID", in = ParameterIn.PATH)
            }
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204", description = "Sale inactivated successfully",
                    content = @Content()
            ),
            @ApiResponse(
                    responseCode = "404", description = "Sale not found",
                    content = @Content()
            )
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        boolean exists = saleService.existsById(id);

        if (!exists) throw new SaleNotFoundException();

        saleService.delete(id);
    }

}
