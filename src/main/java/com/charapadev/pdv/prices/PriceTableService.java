package com.charapadev.pdv.prices;

import com.charapadev.pdv.base.configurations.PriceTableConfiguration;
import com.charapadev.pdv.prices.dtos.CreatePriceTable;
import com.charapadev.pdv.prices.entities.PriceTable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceTableService {

    private final PriceTableRepository priceTableRepository;
    private final PriceTableConfiguration priceTableConfiguration;

    public PriceTableService(PriceTableRepository priceTableRepository, PriceTableConfiguration priceTableConfiguration) {
        this.priceTableRepository = priceTableRepository;
        this.priceTableConfiguration = priceTableConfiguration;
    }


    // Get the default price table based on configuration
    public PriceTable getDefaultTable() {
        return priceTableRepository.findDefault(priceTableConfiguration.getPriceTable());
    }


    // List all price tables available
    public List<PriceTable> findAll() {
        return priceTableRepository.findAll();
    }


    // Generates a new price table
    public PriceTable create(CreatePriceTable data) {
        PriceTable priceTable = new PriceTable();
        priceTable.setName(data.name());
        priceTable.setDescription(data.description());

        return priceTableRepository.save(priceTable);
    }

}
