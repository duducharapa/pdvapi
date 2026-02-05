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

    public PriceItem create(PriceTable table, Product product, BigDecimal price) {
        PriceItem newPrice = new PriceItem();
        newPrice.setProduct(product);
        newPrice.setPrice(price);
        newPrice.setPriceTable(table);

        return priceItemRepository.save(newPrice);
    }

    public PriceItem create(Product product, BigDecimal price) {
        PriceTable defaultTable = priceTableService.getDefaultTable();

        return create(defaultTable, product, price);
    }

    public PriceItem findByProductAndTable(Long productId, Long tableId) {
        return priceItemRepository.findByTableAndProduct(productId, tableId, priceTableConfiguration.getPriceTable());
    }

    public PriceItem findByProductAndTable(Long productId) {
        Long defaultTableId = priceTableService.getDefaultTable().getId();
        return findByProductAndTable(productId, defaultTableId);
    }

    public void save(PriceItem priceItem) {
        priceItemRepository.save(priceItem);
    }

}
