package com.charapadev.pdv.stocks.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "stocks")
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private boolean active = true;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<StockItem> items = new HashSet<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<StockItem> getItems() {
        return items;
    }

    public void addItem(StockItem item) {
        this.items.add(item);
    }
}
