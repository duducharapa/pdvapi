package com.charapadev.pdv.sales;

import com.charapadev.pdv.base.exceptions.NotFoundException;
import com.charapadev.pdv.products.ProductService;
import com.charapadev.pdv.products.entities.Product;
import com.charapadev.pdv.sales.dtos.AddItemSale;
import com.charapadev.pdv.sales.dtos.AddSale;
import com.charapadev.pdv.sales.entities.ItemSale;
import com.charapadev.pdv.sales.entities.Sale;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleService {

    private final SaleRepository saleRepository;
    private final ProductService productService;
    private final ItemSaleRepository itemSaleRepository;

    public SaleService(SaleRepository saleRepository, ProductService productService, ItemSaleRepository itemSaleRepository) {
        this.saleRepository = saleRepository;
        this.productService = productService;
        this.itemSaleRepository = itemSaleRepository;
    }

    private boolean existsById(Long id) {
        return saleRepository.existsById(id);
    }

    public List<Sale> findAll() {
        return saleRepository.findAllWithItems();
    }

    public void create(AddSale data) {
        Sale newSale = new Sale();
        newSale = saleRepository.save(newSale);

        for (AddItemSale itemData: data.items()) {
            ItemSale item = new ItemSale();
            Product productFound = productService.findById(itemData.productId());

            item.setProduct(productFound);
            item.setSale(newSale);
            item.setQuantity(itemData.quantity());
            itemSaleRepository.save(item);
        }
    }

    public Sale findById(Long id) {
        return saleRepository.findByIdWithItems(id).orElseThrow(NotFoundException::new);
    }

    public void delete(Long id) {
        boolean exists = existsById(id);
        if (!exists) throw new NotFoundException();

        saleRepository.deleteById(id);
    }

}
