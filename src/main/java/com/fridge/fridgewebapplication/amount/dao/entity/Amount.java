package com.fridge.fridgewebapplication.amount.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fridge.fridgewebapplication.product.dao.entity.Product;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Amount {

    private int id;
    public Unit unit;
    private int amount;
    private LocalDate expiration_date;
    private Product product;

    public Amount(Unit unit, int amount, LocalDate expiration_date) {
        this.unit = unit;
        this.amount = amount;
        this.expiration_date = expiration_date;
    }

    public Amount() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "amount_id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NotNull
    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public LocalDate getExpiration_date() {
        return expiration_date;
    }

    public void setExpiration_date(LocalDate expiration_date) {
        this.expiration_date = expiration_date;
    }

    @JoinColumn(name = "product_id")
    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
