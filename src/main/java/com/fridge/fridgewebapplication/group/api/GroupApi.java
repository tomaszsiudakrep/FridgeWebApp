package com.fridge.fridgewebapplication.group.api;

import com.fridge.fridgewebapplication.group.dao.entity.Group;
import com.fridge.fridgewebapplication.group.manager.GroupManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/group")
public class GroupApi {

    private GroupManager groupManager;

    @Autowired
    public GroupApi(GroupManager groupManager) {
        this.groupManager = groupManager;
    }

    @GetMapping("/all")
    public Iterable<Group> getAll() {
        return groupManager.findAll();
    }

    @GetMapping
    public Optional<Group> getById(@RequestParam int id) {
        return groupManager.findById(id);
    }

    @PostMapping
    public Group addGroup(@RequestBody Group group) {
        return groupManager.save(group);
    }

    @PutMapping
    public Group updateGroup(@RequestBody Group group) {
        return groupManager.save(group);
    }

    @DeleteMapping
    public void deleteGroup(@RequestParam int id) {
        groupManager.deleteById(id);
    }
}
