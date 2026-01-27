package com.charapadev.pdv.prices.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "price-table")
public class PriceTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<PriceItem>  priceItems = new HashSet<>();

    @Column(nullable = false)
    private String name;

    @Column
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<PriceItem> getPriceItems() {
        return priceItems;
    }

    public void setPriceItems(Set<PriceItem> priceItems) {
        this.priceItems = priceItems;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof PriceTable that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "PriceTable{" +
                "id=" + id +
                ", priceItems=" + priceItems +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
