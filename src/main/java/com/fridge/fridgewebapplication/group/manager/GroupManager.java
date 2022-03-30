package com.fridge.fridgewebapplication.group.manager;

import com.fridge.fridgewebapplication.group.dao.GroupRepo;
import com.fridge.fridgewebapplication.group.dao.entity.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupManager {

    private GroupRepo groupRepo;

    @Autowired
    public GroupManager(GroupRepo groupRepo) {
        this.groupRepo = groupRepo;
    }

    public List<Group> findAll() {
        return groupRepo.findAll();
    }

    public Optional<Group> findById(int id) {
        return groupRepo.findById(id);
    }

    public Group save(Group group) {
        return groupRepo.save(group);
    }

    public void deleteById(int id) {
        groupRepo.deleteById(id);
    }

    public Group findByName(String name) {
        return groupRepo.findByName(name);
    }

    public Iterable<Group> findByArchived(boolean value) {
        return groupRepo.findByArchived(value);
    }

//    @EventListener(ApplicationReadyEvent.class)
//    public void fillDb() {
//        save(new Group("Nabial"));
//        save(new Group("Napoje"));
//        save(new Group("Mięso"));
//        save(new Group("Owoce"));
//    }
}
