package com.charapadev.pdv.products;

import com.charapadev.pdv.base.exceptions.NotFoundException;
import com.charapadev.pdv.payments.exceptions.PaymentNotFoundException;
import com.charapadev.pdv.prices.PriceItemService;
import com.charapadev.pdv.prices.entities.PriceItem;
import com.charapadev.pdv.products.dtos.CreateProduct;
import com.charapadev.pdv.products.dtos.UpdateProduct;
import com.charapadev.pdv.products.entities.Product;
import com.charapadev.pdv.products.exceptions.ProductNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final PriceItemService  priceItemService;

    public ProductService(ProductRepository productRepository,  PriceItemService priceItemService) {
        this.productRepository = productRepository;
        this.priceItemService = priceItemService;
    }

    private boolean existsById(Long id) {
        return productRepository.existsById(id);
    }

    private Product getOrThrow(Long id) throws NotFoundException {
        return  productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product create(CreateProduct data) {
        Product newProduct = new Product();
        newProduct.setName(data.name());

        newProduct = productRepository.save(newProduct);

        BigDecimal defaultPrice = data.price() == null ? BigDecimal.ZERO : data.price();
        priceItemService.create(newProduct, defaultPrice);

        return newProduct;
    }

    public Product findById(Long id) throws NotFoundException {
        return getOrThrow(id);
    }

    public void update(Long productId, UpdateProduct data) throws NotFoundException {
        Product product = getOrThrow(productId);

        if (data.name() != null) {
            product.setName(data.name());
        }

        if (data.price() != null) {
            PriceItem price = priceItemService.findByProductAndTable(productId);
            price.setPrice(data.price());

            priceItemService.save(price);
        }

        productRepository.save(product);
    }

    public void delete(Long id) throws NotFoundException {
        boolean exists = existsById(id);
        if (!exists) throw new PaymentNotFoundException();

        productRepository.deleteById(id);
    }
}
