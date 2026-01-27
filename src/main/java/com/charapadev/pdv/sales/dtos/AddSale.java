package com.charapadev.pdv.sales.dtos;

import java.util.List;

public record AddSale(
        List<AddItemSale> items,
        List<AddPaymentMethod> payments,
        Long priceTableId
) {
}
