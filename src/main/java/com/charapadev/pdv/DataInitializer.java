package com.charapadev.pdv;

import com.charapadev.pdv.base.configurations.PaymentMethodConfiguration;
import com.charapadev.pdv.base.configurations.PriceTableConfiguration;
import com.charapadev.pdv.payments.PaymentMethodService;
import com.charapadev.pdv.payments.dtos.CreatePaymentMethod;
import com.charapadev.pdv.prices.PriceTableService;
import com.charapadev.pdv.prices.dtos.CreatePriceTable;
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

    public DataInitializer(
            PriceTableService priceTableService,
            PriceTableConfiguration priceTableConfiguration,
            PaymentMethodConfiguration paymentMethodConfiguration,
            PaymentMethodService paymentMethodService
    ) {
        this.priceTableService = priceTableService;
        this.priceTableConfiguration = priceTableConfiguration;
        this.paymentMethodConfiguration = paymentMethodConfiguration;
        this.paymentMethodService = paymentMethodService;
    }

    @Override
    public void run(ApplicationArguments args) {
        createDefaultTable();
        createDefaultPaymentMethods();
    }

    private void createDefaultTable() {
        if (priceTableService.getDefaultTable() != null) return;

        priceTableService.create(
                new CreatePriceTable(priceTableConfiguration.getPriceTable(), "Tabela padrão do sistema")
        );
    }

    private void createDefaultPaymentMethods() {
        List<String> paymentMethods = paymentMethodConfiguration.getPaymentMethods();

        paymentMethods.forEach(paymentName -> {
            if (paymentMethodService.find(paymentName) == null) {
                CreatePaymentMethod data = new CreatePaymentMethod(paymentName);

                paymentMethodService.create(data);
            }
        });
    }

}
