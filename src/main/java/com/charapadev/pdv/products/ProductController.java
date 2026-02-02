package com.charapadev.pdv.products;

import com.charapadev.pdv.products.dtos.CreateProduct;
import com.charapadev.pdv.products.dtos.UpdateProduct;
import com.charapadev.pdv.products.entities.Product;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Products", description = "Represents the goods to manage and sell")
@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @Operation(
            summary = "List products",
            description = "Lists all products registered on application"
    )
    @ApiResponse(
            description = "Products found successfully", responseCode = "200",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    array = @ArraySchema(schema = @Schema(implementation = Product.class))
            )
    )
    @GetMapping
    public List<Product> list() {
        return productService.findAll();
    }


    @Operation(
            summary = "Create product",
            description = "Generates a new product if does not exists any with the same name"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201", description = "Product created successfully",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Product.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "422", description = "Product with the given name already registered",
                    content = @Content()
            )
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Product create(@Valid @RequestBody CreateProduct data) {
        return productService.create(data);
    }


    @Operation(
            summary = "Search product",
            description = "Searches for a product using the given identifier and returns it if found",
            parameters = {
                    @Parameter(name = "id", description = "Product ID", in = ParameterIn.PATH)
            }
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Product found successfully",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Product.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404", description = "Product not found",
                    content = @Content()
            )
    })
    @GetMapping("/{id}")
    public Product search(@PathVariable Long id) {
        return productService.findById(id);
    }


    @Operation(
            summary = "Edit product",
            description = "Updates a product identified by ID using the given data provided on request body",
            parameters = {
                    @Parameter(name = "id", description = "Product ID", in = ParameterIn.PATH)
            }
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204", description = "Product updated successfully",
                    content = @Content()
            ),
            @ApiResponse(
                    responseCode = "404", description = "Product not found",
                    content = @Content()
            )
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody UpdateProduct data) {
        productService.update(id, data);
    }


    // TODO: Adicionar verificação se o produto foi utilizado ou não
    @Operation(
            summary = "Remove product",
            description = "Marks the given product identified by ID as inactive or, if not used in any sale, " +
                    "deletes it",
            parameters = {
                    @Parameter(name = "id", description = "Product ID", in = ParameterIn.PATH)
            }
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204", description = "Product removed successfully",
                    content = @Content()
            ),
            @ApiResponse(
                    responseCode = "404", description = "Product not found",
                    content = @Content()
            )
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }

}
