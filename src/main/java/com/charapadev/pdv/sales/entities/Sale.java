package com.charapadev.pdv.sales.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "sales")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sale")
    Set<ItemSale> itemSales = new HashSet<>();

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
