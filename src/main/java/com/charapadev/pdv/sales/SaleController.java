package com.charapadev.pdv.sales;

import com.charapadev.pdv.prices.PriceTableService;
import com.charapadev.pdv.sales.dtos.AddSale;
import com.charapadev.pdv.sales.entities.Sale;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sales")
public class SaleController {

    private final SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @GetMapping
    public List<Sale> list() {
        return saleService.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void add(@RequestBody AddSale data) {
        saleService.create(data);
    }

    @GetMapping("/{id}")
    public Sale search(@PathVariable Long id) {
        return saleService.findById(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        saleService.delete(id);
    }

}
