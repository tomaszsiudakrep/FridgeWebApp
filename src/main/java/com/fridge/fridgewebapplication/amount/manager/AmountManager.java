package com.fridge.fridgewebapplication.amount.manager;

import com.fridge.fridgewebapplication.amount.dao.AmountRepo;
import com.fridge.fridgewebapplication.amount.dao.entity.Amount;
import com.fridge.fridgewebapplication.amount.dao.entity.Unit;
import com.fridge.fridgewebapplication.product.dao.ProductRepo;
import com.fridge.fridgewebapplication.product.dao.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class AmountManager {

    private AmountRepo amountRepo;
    @Autowired
    private ProductRepo productRepo;

    @Autowired
    public AmountManager(AmountRepo amountRepo) {
        this.amountRepo = amountRepo;
    }

    public Amount save(Amount amount, String productName) {
        Product product = productRepo.findByName(productName);
        amount.setProduct(product);
        return amountRepo.save(amount);
    }

    public Iterable<Amount> getAll() {
        return amountRepo.findAll();
    }

    public void deleteById(int id) {
        amountRepo.deleteById(id);
    }

    public void deleteAll() {
        amountRepo.deleteAll();
    }

    public Amount update(Amount amount, String productName) {
        Product product = productRepo.findByName(productName);
        amount.setProduct(product);
        return amountRepo.save(amount);
    }
}
