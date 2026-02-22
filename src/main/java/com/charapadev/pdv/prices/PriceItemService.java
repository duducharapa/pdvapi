package com.charapadev.pdv.prices;

import com.charapadev.pdv.base.configurations.PriceTableConfiguration;
import com.charapadev.pdv.prices.entities.PriceItem;
import com.charapadev.pdv.prices.entities.PriceTable;
import com.charapadev.pdv.products.entities.Product;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PriceItemService {

    private final PriceItemRepository priceItemRepository;
    private final PriceTableService priceTableService;
    private final PriceTableConfiguration priceTableConfiguration;

    public PriceItemService(PriceItemRepository priceItemRepository, PriceTableService priceTableService, PriceTableConfiguration priceTableConfiguration) {
        this.priceItemRepository = priceItemRepository;
        this.priceTableService = priceTableService;
        this.priceTableConfiguration = priceTableConfiguration;
    }


    // Creates a new relation about a product and one price table
    public PriceItem create(PriceTable table, Product product, BigDecimal price) {
        PriceItem newPrice = new PriceItem();
        newPrice.setProduct(product);
        newPrice.setPrice(price);
        newPrice.setPriceTable(table);

        return priceItemRepository.save(newPrice);
    }

    // Also creates a new relation, but refers automatically to the default price table
    public PriceItem create(Product product, BigDecimal price) {
        PriceTable defaultTable = priceTableService.getDefaultTable();

        return create(defaultTable, product, price);
    }


    // Searches a price using the identifiers of product and table related
    public PriceItem findByProductAndTable(Long productId, Long tableId) {
        return priceItemRepository.findByTableAndProduct(productId, tableId, priceTableConfiguration.getPriceTable());
    }


    // Searches a price too, but the default table is used
    public PriceItem findByProductAndTable(Long productId) {
        Long defaultTableId = priceTableService.getDefaultTable().getId();
        return findByProductAndTable(productId, defaultTableId);
    }


    // Records the changes on a new or existent price item
    public void save(PriceItem priceItem) {
        priceItemRepository.save(priceItem);
    }

}
