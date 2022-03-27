package com.fridge.fridgewebapplication.group.api;

import com.fridge.fridgewebapplication.group.GroupNotFoundException;
import com.fridge.fridgewebapplication.group.dao.entity.Group;
import com.fridge.fridgewebapplication.group.dto.GroupDto;
import com.fridge.fridgewebapplication.group.manager.GroupManager;
import com.fridge.fridgewebapplication.group.mapper.GroupMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/group")
public class GroupApi {

    private GroupManager groupManager;
    private GroupMapper groupMapper;

    @Autowired
    public GroupApi(GroupManager groupManager, GroupMapper groupMapper) {
        this.groupManager = groupManager;
        this.groupMapper = groupMapper;
    }


    @GetMapping("/all")
    public List<GroupDto> getAll() {
        return groupMapper.mapToGroupDtoList(groupManager.findAll());
    }

    @GetMapping
    public GroupDto getById(@RequestParam int id) throws GroupNotFoundException {
        return groupMapper.mapToGroupDto(groupManager.findById(id).orElseThrow(GroupNotFoundException::new));
    }

    @PostMapping
    public void addGroup(@RequestBody GroupDto groupDto) {
        Group group = groupMapper.mapToGroup(groupDto);
        groupManager.save(group);
    }

    @PutMapping
    public GroupDto updateGroup(@RequestBody GroupDto groupDto) {
        Group group = groupMapper.mapToGroup(groupDto);
        Group savedGroup = groupManager.save(group);
        return groupMapper.mapToGroupDto(savedGroup);
    }

    @DeleteMapping
    public void deleteGroup(@RequestParam int id) throws GroupNotFoundException{
        groupManager.deleteById(id);
    }
}
