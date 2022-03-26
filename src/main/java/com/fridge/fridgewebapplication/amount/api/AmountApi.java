package com.fridge.fridgewebapplication.amount.api;

import com.fridge.fridgewebapplication.amount.dao.entity.Amount;
import com.fridge.fridgewebapplication.amount.manager.AmountManager;
import com.fridge.fridgewebapplication.group.dao.entity.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/amount")
public class AmountApi {

    private AmountManager amountManager;

    @Autowired
    public AmountApi(AmountManager amountManager) {
        this.amountManager = amountManager;
    }

    @GetMapping("/all")
    public Iterable<Amount> getAll() {
        return amountManager.getAll();
    }

    @PostMapping
    public Amount addGroup(@RequestBody Amount amount, @RequestParam String productName) {
        return amountManager.save(amount, productName);
    }

    @DeleteMapping
    public void deleteAmount(@RequestParam int id) {
        amountManager.deleteById(id);
    }

    @DeleteMapping("/all")
    public void deleteAmount() {
        amountManager.deleteAll();
    }

    @PutMapping
    public Amount update(@RequestBody Amount amount, @RequestParam String productName) {
        return amountManager.save(amount, productName);
    }
}
