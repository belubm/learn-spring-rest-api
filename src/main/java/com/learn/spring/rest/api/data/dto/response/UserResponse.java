package com.learn.spring.rest.api.data.dto.response;

import com.learn.spring.rest.api.data.dao.RoleEntity;

public class UserResponse {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private RoleEntity role;

    public UserResponse() {

    }

    public UserResponse(Integer id, String firstName, String lastName, String email, RoleEntity role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public RoleEntity getRole() {
        return role;
    }

    public void setRole(RoleEntity role) {
        this.role = role;
    }
}
