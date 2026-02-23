package com.charapadev.pdv;

import com.charapadev.pdv.base.configurations.PaymentMethodConfiguration;
import com.charapadev.pdv.base.configurations.PriceTableConfiguration;
import com.charapadev.pdv.base.configurations.StockConfiguration;
import com.charapadev.pdv.payments.PaymentMethodService;
import com.charapadev.pdv.payments.dtos.CreatePaymentMethod;
import com.charapadev.pdv.prices.PriceTableService;
import com.charapadev.pdv.prices.dtos.CreatePriceTable;
import com.charapadev.pdv.stocks.StockService;
import com.charapadev.pdv.stocks.dtos.CreateStock;
import org.jspecify.annotations.NonNull;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializer implements ApplicationRunner {

    private final PriceTableService priceTableService;
    private final PriceTableConfiguration priceTableConfiguration;
    private final PaymentMethodConfiguration paymentMethodConfiguration;
    private final PaymentMethodService  paymentMethodService;
    private final StockService stockService;
    private final StockConfiguration stockConfiguration;

    public DataInitializer(
            PriceTableService priceTableService,
            PriceTableConfiguration priceTableConfiguration,
            PaymentMethodConfiguration paymentMethodConfiguration,
            PaymentMethodService paymentMethodService,
            StockService stockService,
            StockConfiguration stockConfiguration
    ) {
        this.priceTableService = priceTableService;
        this.priceTableConfiguration = priceTableConfiguration;
        this.paymentMethodConfiguration = paymentMethodConfiguration;
        this.paymentMethodService = paymentMethodService;
        this.stockService = stockService;
        this.stockConfiguration = stockConfiguration;
    }

    @Override
    public void run(@NonNull ApplicationArguments args) {
        createDefaultTable();
        createDefaultPaymentMethods();
        createDefaultStock();
    }


    //  Create basic price table on application for default products prices
    private void createDefaultTable() {
        if (priceTableService.getDefaultTable() != null) return;

        priceTableService.create(
                new CreatePriceTable(priceTableConfiguration.getPriceTable(), "Tabela padrão do sistema")
        );
    }


    // Create the default basics method payments available to use on application
    private void createDefaultPaymentMethods() {
        List<String> paymentMethods = paymentMethodConfiguration.getPaymentMethods();

        paymentMethods.forEach(paymentName -> {
            if (paymentMethodService.find(paymentName) == null) {
                CreatePaymentMethod data = new CreatePaymentMethod(paymentName);

                paymentMethodService.create(data);
            }
        });
    }


    // Create the default stock for application default quantities
    private void createDefaultStock() {
        if (stockService.getDefaultStock() != null) return;

        stockService.create(
                new CreateStock(stockConfiguration.getStock())
        );
    }

}
