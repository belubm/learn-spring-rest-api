package com.learn.spring.rest.api.data.dto.response;

public class RoleResponse {

    private short id;
    private String description;

    public RoleResponse() {
    }

    public RoleResponse(short id, String description) {
        this.id = id;
        this.description = description;
    }

    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
