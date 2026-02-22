package com.charapadev.pdv.stocks;

import com.charapadev.pdv.stocks.dtos.CreateStock;
import com.charapadev.pdv.stocks.dtos.UpdateStock;
import com.charapadev.pdv.stocks.entities.Stock;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockService {

    private final StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }


    //
    public boolean exists(Long id) {
        return stockRepository.existsById(id);
    }


    // Lists all stocks available
    public List<Stock> findAll() {
        return stockRepository.findAll();
    }


    // Generates a new stock on application
    public Stock create(CreateStock createStock) {
        Stock stock = new Stock();

        stock.setName(createStock.name());

        return stockRepository.save(stock);
    }


    // Searches for a stock or returns a null reference
    public Stock findById(Long id) {
        return stockRepository.findById(id).orElse(null);
    }


    // Update a specific stock using the given data
    public void update(Stock stock, UpdateStock data) {
        String stockName = data.name();
        boolean inputNameBlank = stockName.isBlank();

        if (!inputNameBlank) {
            stock.setName(stockName);
        }

        stockRepository.save(stock);
    }


    // Makes a soft delete on specific stock
    @Transactional
    public void delete(Long id) {
        stockRepository.markAsInactive(id);
    }

}
