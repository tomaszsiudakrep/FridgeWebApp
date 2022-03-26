package com.fridge.fridgewebapplication.product.dao;

import com.fridge.fridgewebapplication.product.dao.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface ProductRepo extends CrudRepository<Product, Integer> {


}
