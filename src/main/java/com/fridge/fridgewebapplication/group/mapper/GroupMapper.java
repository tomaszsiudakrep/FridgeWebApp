package com.fridge.fridgewebapplication.group.mapper;

import com.fridge.fridgewebapplication.group.dao.entity.Group;
import com.fridge.fridgewebapplication.group.dto.GroupDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GroupMapper {

    public Group mapToGroup(final GroupDto groupDto) {
        return new Group(groupDto.getName());
    }

    public GroupDto mapToGroupDto(final Group group) {
        return new GroupDto(group.getId(), group.getName(), group.isArchived());
    }

    public List<GroupDto> mapToGroupDtoList(final List<Group> groupList) {
        return groupList.stream().map(this::mapToGroupDto).collect(Collectors.toList());
    }

}
