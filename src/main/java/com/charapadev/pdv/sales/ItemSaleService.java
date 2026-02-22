package com.charapadev.pdv.sales;

import org.springframework.stereotype.Service;

@Service
public class ItemSaleService {

    private final ItemSaleRepository itemSaleRepository;

    public ItemSaleService(ItemSaleRepository itemSaleRepository) {
        this.itemSaleRepository = itemSaleRepository;
    }


    // Check if a specific product is already used on any sale
    public boolean isProductVinculated(Long id) {
        return itemSaleRepository.countByProduct(id) > 0;
    }

}
