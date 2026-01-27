package com.charapadev.pdv.configurations;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "pdv.default")
public class PriceTableConfiguration {

    private String priceTable;

    public String getPriceTable() {
        return priceTable;
    }

    public void setPriceTable(String priceTable) {
        this.priceTable = priceTable;
    }

}
