package com.fridge.fridgewebapplication.group.dao;

import com.fridge.fridgewebapplication.group.dao.entity.Group;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface GroupRepo extends CrudRepository<Group, Integer> {

    Group findByName(String name);

    List<Group> findByArchived(boolean value);

    List<Group> findAll();

    Optional<Group> findById(Integer integer);
}
