package com.learn.spring.rest.api.data.dto.request;

public class RoleRequest {

    private String description;

    public RoleRequest() {
    }

    public RoleRequest(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
