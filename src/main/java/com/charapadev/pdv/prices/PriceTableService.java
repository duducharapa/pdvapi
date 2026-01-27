package com.charapadev.pdv.prices;

import com.charapadev.pdv.base.exceptions.NotFoundException;
import com.charapadev.pdv.prices.dtos.CreatePriceTable;
import com.charapadev.pdv.prices.entities.PriceTable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceTableService {

    private final PriceTableRepository priceTableRepository;

    public PriceTableService(PriceTableRepository priceTableRepository) {
        this.priceTableRepository = priceTableRepository;
    }

    public PriceTable getDefaultTable() {
        return priceTableRepository.findDefault();
    }

    public List<PriceTable> findAll() {
        return priceTableRepository.findAll();
    }

    public PriceTable create(CreatePriceTable data) {
        PriceTable priceTable = new PriceTable();
        priceTable.setName(data.name());
        priceTable.setDescription(data.description());

        return priceTableRepository.save(priceTable);
    }

    public PriceTable findById(Long id) throws NotFoundException {
        return priceTableRepository.findById(id).orElseThrow(NotFoundException::new);
    }

}
