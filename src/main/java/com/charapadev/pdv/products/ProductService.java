package com.charapadev.pdv.products;

import com.charapadev.pdv.base.exceptions.NotFoundException;
import com.charapadev.pdv.products.dtos.CreateProduct;
import com.charapadev.pdv.products.dtos.UpdateProduct;
import com.charapadev.pdv.products.entities.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    private boolean existsById(Long id) {
        return productRepository.existsById(id);
    }

    private Product getOrThrow(Long id) throws NotFoundException {
        return  productRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product create(CreateProduct data) {
        Product newProduct = new Product();
        newProduct.setName(data.name());

        return productRepository.save(newProduct);
    }

    public Product findById(Long id) throws NotFoundException {
        return getOrThrow(id);
    }

    public void update(Long productId, UpdateProduct data) throws NotFoundException {
        Product product = getOrThrow(productId);

        if (data.name() != null) {
            product.setName(data.name());
        }

        productRepository.save(product);
    }

    public void delete(Long id) throws NotFoundException {
        boolean exists = existsById(id);
        if (!exists) throw new NotFoundException();

        productRepository.deleteById(id);
    }
}
