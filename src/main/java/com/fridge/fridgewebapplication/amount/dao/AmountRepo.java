package com.fridge.fridgewebapplication.amount.dao;

import com.fridge.fridgewebapplication.amount.dao.entity.Amount;
import com.fridge.fridgewebapplication.amount.dao.entity.Unit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Transient;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface AmountRepo extends CrudRepository<Amount, Integer> {

    List<Amount> findByUnit(Unit unit);

    List<Amount> findAll();




}
