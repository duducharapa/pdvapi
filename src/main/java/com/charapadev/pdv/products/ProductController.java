package com.charapadev.pdv.products;

import com.charapadev.pdv.products.dtos.CreateProduct;
import com.charapadev.pdv.products.dtos.UpdateProduct;
import com.charapadev.pdv.products.entities.Product;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> list() {
        return productService.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Product create(@Valid @RequestBody CreateProduct data) {
        return productService.create(data);
    }

    @GetMapping("/{id}")
    public Product search(@PathVariable Long id) {
        return productService.findById(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody UpdateProduct data) {
        productService.update(id, data);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }

}
