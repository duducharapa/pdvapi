package com.charapadev.pdv.prices;

import com.charapadev.pdv.prices.dtos.CreatePriceTable;
import com.charapadev.pdv.prices.entities.PriceTable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/price-tables")
public class PriceTableController {

    private final PriceTableService priceTableService;

    public PriceTableController(PriceTableService priceTableService) {
        this.priceTableService = priceTableService;
    }

    @GetMapping
    public List<PriceTable> list() {
        return priceTableService.findAll();
    }

    @PostMapping
    public PriceTable create(@RequestBody CreatePriceTable data) {
        return priceTableService.create(data);
    }

}
