package com.fridge.fridgewebapplication.product.manager;

import com.fridge.fridgewebapplication.group.dao.GroupRepo;
import com.fridge.fridgewebapplication.group.dao.entity.Group;
import com.fridge.fridgewebapplication.product.dao.ProductRepo;
import com.fridge.fridgewebapplication.product.dao.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductManager {

    private ProductRepo productRepo;
    @Autowired
    private GroupRepo groupRepo;

    @Autowired
    public ProductManager(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public Iterable<Product> getAll() {
        return productRepo.findAll();
    }

    public Optional<Product> getById(int id) {
        return productRepo.findById(id);
    }

    public Product save(Product product, String group) {
        product.setGroup(groupRepo.findByName(group));
        return productRepo.save(product);
    }

    public Product update(Product product, String group) {
        product.setGroup(groupRepo.findByName(group));
        return productRepo.save(product);
    }

    public void deleteById(int id) {
        productRepo.deleteById(id);
    }

//    @EventListener(ApplicationReadyEvent.class)
//    public void fillProductDb() {
//        Product product = new Product("Bananas");
//        save(product, "Owoce");
//    }

}
