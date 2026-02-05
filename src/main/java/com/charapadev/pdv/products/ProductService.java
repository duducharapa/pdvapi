package com.charapadev.pdv.products;

import com.charapadev.pdv.base.exceptions.NotFoundException;
import com.charapadev.pdv.prices.PriceItemService;
import com.charapadev.pdv.prices.entities.PriceItem;
import com.charapadev.pdv.products.dtos.CreateProduct;
import com.charapadev.pdv.products.dtos.UpdateProduct;
import com.charapadev.pdv.products.entities.Product;
import com.charapadev.pdv.sales.ItemSaleService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final PriceItemService  priceItemService;
    private final ItemSaleService itemSaleService;

    public ProductService(
            ProductRepository productRepository,
            PriceItemService priceItemService,
            ItemSaleService itemSaleService) {
        this.productRepository = productRepository;
        this.priceItemService = priceItemService;
        this.itemSaleService = itemSaleService;
    }

    public boolean existsById(Long id) {
        return productRepository.existsById(id);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }


    // Creates a new product with default price if it's provided
    // The PriceItem must be created even if the value is not passed to avoid inconsistencies
    public Product create(CreateProduct data) {
        Product newProduct = new Product();
        newProduct.setName(data.name());

        newProduct = productRepository.save(newProduct);

        BigDecimal defaultPrice = data.price() == null ? BigDecimal.ZERO : data.price();
        PriceItem createdPrice = priceItemService.create(newProduct, defaultPrice);
        newProduct.addPrice(createdPrice);

        return newProduct;
    }


    // Returns the product found or a null object
    public Product findById(Long id) throws NotFoundException {
        return productRepository.findById(id).orElse(null);
    }


    // Updates the data about a specific product
    public void update(Product product, UpdateProduct data) throws NotFoundException {
        if (data.name() != null) {
            product.setName(data.name());
        }

        if (data.price() != null) {
            PriceItem price = priceItemService.findByProductAndTable(product.getId());
            price.setPrice(data.price());

            priceItemService.save(price);
        }

        productRepository.save(product);
    }


    // Marks a product as inactive if it was used in some operation. Otherwise, removes it
    @Transactional
    public void delete(Long id) throws NotFoundException {
        boolean isVinculated = itemSaleService.isProductVinculated(id);

        if (isVinculated) {
            productRepository.markAsInactive(id);
        } else {
            productRepository.deleteById(id);
        }
    }
}
