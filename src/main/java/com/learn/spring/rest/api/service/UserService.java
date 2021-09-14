package com.learn.spring.rest.api.service;

import com.learn.spring.rest.api.data.dao.RoleEntity;
import com.learn.spring.rest.api.data.dao.UserEntity;
import com.learn.spring.rest.api.exceptions.RoleServiceException;
import com.learn.spring.rest.api.exceptions.UserServiceException;
import com.learn.spring.rest.api.repository.RoleRepository;
import com.learn.spring.rest.api.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    protected RoleRepository roleRepository;

    public UserEntity createUser(Short roleId, UserEntity userEntity) {
        if (userRepository.findByEmail(userEntity.getEmail()) != null) {
            throw new UserServiceException("Record with email address: " + userEntity.getEmail() + " already exists! Please provide an unique email address.");
        }
        RoleEntity role = roleRepository.findById(roleId).orElseThrow(() -> new RoleServiceException("Role with id: " + roleId + " not found"));
        userEntity.setRole(role);
        return userRepository.save(userEntity);
    }

    public List<UserEntity> getUsers() {
        return (List<UserEntity>) userRepository.findAll();
    }

    public UserEntity getUser(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new UserServiceException("User with id: " + id + " not found"));
    }


    public UserEntity updateUser(Short roleId, Integer userId, UserEntity userEntity) {
        UserEntity returnValue = userRepository.findById(userId).orElseThrow(() -> new UserServiceException("User with id: " + userId + " not found!"));
        if (userRepository.findByEmail(userEntity.getEmail()) != null && !userRepository.findByEmail(userEntity.getEmail()).getEmail().equals(returnValue.getEmail())) {
            throw new UserServiceException("Record with email address: " + userEntity.getEmail() + " already exists! Please provide an unique email address.");
        }
        BeanUtils.copyProperties(userEntity, returnValue);
        returnValue.setId(userId);
        returnValue.setRole(roleRepository.findById(roleId).orElseThrow(() -> new RoleServiceException("Role with id: " + roleId + " not found")));
        return userRepository.save(returnValue);
    }

    public void deleteUser(Integer id) {
        userRepository.delete(userRepository.findById(id).orElseThrow(() -> new UserServiceException("User with id: " + id + " not found")));
    }

    public List<String> getEmailList() {
        List<String> returnValue = new ArrayList<>();
        List<com.learn.spring.rest.api.data.dao.UserEntity> users = (List<com.learn.spring.rest.api.data.dao.UserEntity>) userRepository.findAll();
        for (com.learn.spring.rest.api.data.dao.UserEntity user : users) {
            String email = user.getEmail();
            returnValue.add(email);
        }
        return returnValue;
    }

    public String getUserEmail(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new UserServiceException("User with id: " + id + " not found")).getEmail();
    }
}
