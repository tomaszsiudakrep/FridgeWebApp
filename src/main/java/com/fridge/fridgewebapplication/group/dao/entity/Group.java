package com.fridge.fridgewebapplication.group.dao.entity;

import com.fridge.fridgewebapplication.product.dao.entity.Product;
import com.sun.istack.NotNull;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product_group")
public class Group {

    private int id;
    private String name;
    private LocalDate created_date;
    private boolean archived;
    private LocalDate date_archived;
    private List<Product> product = new ArrayList<>();


    public Group(String name) {
        this.name = name;
        created_date = LocalDate.now();
        archived = false;
        date_archived = null;
    }

    public Group() {
    }


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @NotNull
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NotNull
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull
    public LocalDate getCreated_date() {
        return created_date;
    }

    public void setCreated_date(LocalDate created_date) {
        this.created_date = created_date;
    }

    @NotNull
    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    public LocalDate getDate_archived() {
        return date_archived;
    }

    public void setDate_archived(LocalDate date_archived) {
        this.date_archived = date_archived;
    }

    @OneToMany(
            targetEntity = Product.class,
            mappedBy = "id",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    public List<Product> getProduct() {
        return product;
    }

    public void setProduct(List<Product> product) {
        this.product = product;
    }
}
