package com.charapadev.pdv.sales.dtos;

import java.math.BigDecimal;

public record AddPaymentMethod(
        Long methodId,
        BigDecimal amount
) {
}
