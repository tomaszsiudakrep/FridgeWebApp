package com.fridge.fridgewebapplication.group.dto;

public class GroupDto {

    private int id;
    private String name;
    private boolean archived;

    public GroupDto(int id, String name, boolean archived) {
        this.id = id;
        this.name = name;
        this.archived = archived;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isArchived() {
        return archived;
    }
}
