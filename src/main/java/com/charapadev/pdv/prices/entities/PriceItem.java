package com.charapadev.pdv.prices.entities;

import com.charapadev.pdv.products.entities.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Objects;

@Schema(description = "Intermediary class that represents each product price in a table")
@Entity
@Table(name = "price-table_items")
public class PriceItem {

    @Schema(description = "Price of product identifier", example = "4")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private PriceTable priceTable;

    @ManyToOne
    @JsonIgnore
    private Product product;

    @Schema(description = "The specific price of product in the referenced prices table", example = "45.67")
    @Column(nullable = false)
    private BigDecimal price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PriceTable getPriceTable() {
        return priceTable;
    }

    public void setPriceTable(PriceTable priceTable) {
        this.priceTable = priceTable;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof PriceItem priceItem)) return false;
        return Objects.equals(id, priceItem.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "PriceItem{" +
                "id=" + id +
                ", priceTable=" + priceTable +
                ", product=" + product +
                ", price=" + price +
                '}';
    }

}
