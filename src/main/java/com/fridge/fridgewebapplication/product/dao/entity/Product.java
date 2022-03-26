package com.fridge.fridgewebapplication.product.dao.entity;

import com.fridge.fridgewebapplication.group.dao.entity.Group;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Product {

    private int id;
    private String name;
    private LocalDate created_date;
    private Group group;
    private boolean archived;
    private LocalDate archived_date;

    public Product(String name) {
        this.name = name;
        this.created_date = LocalDate.now();
        this.archived = false;
        this.archived_date = null;
    }

    public Product() {
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "product_id")
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

    public LocalDate getCreated_date() {
        return created_date;
    }

    public void setCreated_date(LocalDate created_date) {
        this.created_date = created_date;
    }

    @JoinColumn(name = "group_id")
    @ManyToOne()
    @NotNullgit
    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    public LocalDate getArchived_date() {
        return archived_date;
    }

    public void setArchived_date(LocalDate archived_date) {
        this.archived_date = archived_date;
    }
}
