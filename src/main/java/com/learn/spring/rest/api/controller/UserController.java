package com.learn.spring.rest.api.controller;

import com.learn.spring.rest.api.data.dao.UserEntity;
import com.learn.spring.rest.api.data.dto.request.UserRequest;
import com.learn.spring.rest.api.data.dto.response.UserResponse;
import com.learn.spring.rest.api.exceptions.DuplicateRecordException;
import com.learn.spring.rest.api.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v01/users")
public class UserController {

    @Autowired
    protected UserService userService;

    @PostMapping("/role/{roleId}")
    public UserResponse createUser(@PathVariable short roleId, @RequestBody UserRequest userRequest) throws DuplicateRecordException {
        UserResponse returnValue = new UserResponse();
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userRequest, userEntity);
        UserEntity createdUser = null;
        createdUser = userService.createUser(roleId, userEntity);
        BeanUtils.copyProperties(createdUser, returnValue);
        return returnValue;
    }

    @GetMapping("/{id}")
    public UserResponse getUser(@PathVariable int id) {
        UserResponse returnValue = new UserResponse();
        UserEntity user = userService.getUser(id);
        BeanUtils.copyProperties(user, returnValue);
        return returnValue;
    }

    @GetMapping()
    public List<UserResponse> getUsers() {
        List<UserResponse> returnValue = new ArrayList<>();
        List<UserEntity> users = userService.getUsers();
        for (UserEntity user : users) {
            UserResponse userResponse = new UserResponse();
            BeanUtils.copyProperties(user, userResponse);
            returnValue.add(userResponse);
        }
        return returnValue;
    }

    @PutMapping("/{userId}/role/{roleId}")
    public UserResponse updateUser(@PathVariable short roleId, @PathVariable int userId, @RequestBody UserRequest userRequest) {
        UserResponse returnValue = new UserResponse();
        UserEntity entity = new UserEntity();
        BeanUtils.copyProperties(userRequest, entity);
        UserEntity userEntity = userService.updateUser(roleId, userId, entity);
        BeanUtils.copyProperties(userEntity, returnValue);
        return returnValue;
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return "User with id: " + id + " was permanently deleted";
    }

    @GetMapping(value = "/emails")
    public List<String> getEmailList() {
        return userService.getEmailList();
    }

    @GetMapping(value = "/{id}/emails")
    public String getUserEmail(@PathVariable int id) {
        return userService.getUserEmail(id);
    }
}
