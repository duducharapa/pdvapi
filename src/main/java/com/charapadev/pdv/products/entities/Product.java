package com.charapadev.pdv.products.entities;

import com.charapadev.pdv.prices.entities.PriceItem;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Schema(description = "Represents the goods to manage and sell")
@Entity
@Table(name = "products")
public class Product implements Serializable {

    @Schema(description = "Product identifier", example = "3")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "An unique name that also identifies the product on application", example = "Hot chicken")
    @Column(nullable = false, unique = true)
    private String name;

    @Schema(description = "Checks if the product is active for transactions or not", example = "false")
    @Column(nullable = false)
    private boolean active = true;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private Set<PriceItem> prices  = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<PriceItem> getPrices() {
        return prices;
    }

    public void setPrices(Set<PriceItem> prices) {
        this.prices = prices;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void addPrice(PriceItem price) {
        this.prices.add(price);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Product product)) return false;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", prices=" + prices +
                '}';
    }

}
