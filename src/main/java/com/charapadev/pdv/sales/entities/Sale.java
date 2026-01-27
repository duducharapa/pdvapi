package com.charapadev.pdv.sales.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "sales")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal totalValue =  BigDecimal.ZERO;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sale")
    private Set<ItemSale> itemSales = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sale")
    private Set<PaymentSale> paymentSales = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<ItemSale> getItemSales() {
        return itemSales;
    }

    public void setItemSales(Set<ItemSale> itemSales) {
        this.itemSales = itemSales;
    }

    public Set<PaymentSale> getPaymentSales() {
        return paymentSales;
    }

    public void setPaymentSales(Set<PaymentSale> paymentSales) {
        this.paymentSales = paymentSales;
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(BigDecimal totalValue) {
        this.totalValue = totalValue;
    }

    public void increaseAmount(BigDecimal amount) {
        totalValue = totalValue.add(amount);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Sale sale)) return false;
        return Objects.equals(id, sale.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Sale{" +
                "id=" + id +
                ", itemSales=" + itemSales +
                '}';
    }
}
