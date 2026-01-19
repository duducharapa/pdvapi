package com.charapadev.pdv.products;

import com.charapadev.pdv.products.dtos.CreateProduct;
import com.charapadev.pdv.products.dtos.UpdateProduct;
import com.charapadev.pdv.products.entities.Product;
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

    @PostMapping
    public Product create(@RequestBody CreateProduct data) {
        return productService.create(data);
    }

    @GetMapping("/{id}")
    public Product search(@PathVariable Long id) {
        return productService.findById(id);
    }

    @PatchMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody UpdateProduct data) {
        productService.update(id, data);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }

}
