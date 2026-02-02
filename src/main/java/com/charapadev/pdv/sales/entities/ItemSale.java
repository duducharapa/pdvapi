package com.charapadev.pdv.sales.entities;

import com.charapadev.pdv.products.entities.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Schema(description = "Intermediary class that represents each product sold in a sale instance")
@Entity
@Table(name = "sale_items")
public class ItemSale implements Serializable {

    @Schema(description = "Item in sale identifier", example = "21")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Product product;

    @ManyToOne
    @JsonIgnore
    private Sale sale;

    @Schema(description = "The quantity of the vinculated product sold in the specific sale", example = "8")
    @Column(nullable = false)
    private Integer quantity;

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return this.quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ItemSale itemSale)) return false;
        return Objects.equals(id, itemSale.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ItemSale{" +
                "id=" + id +
                ", product=" + product +
                ", quantity=" + quantity +
                '}';
    }
}
