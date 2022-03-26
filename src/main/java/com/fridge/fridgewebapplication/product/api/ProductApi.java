package com.fridge.fridgewebapplication.product.api;

import com.fridge.fridgewebapplication.product.dao.entity.Product;
import com.fridge.fridgewebapplication.product.manager.ProductManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RequestMapping("/api/product")
@RestController
public class ProductApi {

    private ProductManager productManager;

    @Autowired
    public ProductApi(ProductManager productManager) {
        this.productManager = productManager;
    }

    @GetMapping("/all")
    public Iterable<Product> getAll() {
        return productManager.getAll();
    }

    @GetMapping
    public Optional<Product> getById(@RequestParam int id) {
        return productManager.getById(id);
    }

    @PostMapping
    public Product addGroup(@RequestBody Product product, @RequestParam String group) {
        return productManager.save(product, group);
    }

    @PutMapping
    public Product updateGroup(@RequestBody Product product, @RequestParam String group) {
        return productManager.save(product, group);
    }

    @DeleteMapping
    public void deleteGroup(@RequestParam int id) {
        productManager.deleteById(id);
    }
}
