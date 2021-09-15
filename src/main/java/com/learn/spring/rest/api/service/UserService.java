package com.learn.spring.rest.api.service;

import com.learn.spring.rest.api.data.dao.RoleEntity;
import com.learn.spring.rest.api.data.dao.UserEntity;
import com.learn.spring.rest.api.exceptions.DuplicateRecordException;
import com.learn.spring.rest.api.exceptions.RecordNotFoundException;
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

    public UserEntity createUser(Short roleId, UserEntity userEntity) throws DuplicateRecordException {
        if (userRepository.findByEmail(userEntity.getEmail()) != null) {
            throw new DuplicateRecordException("Record with email address: " + userEntity.getEmail() + " already exists! Please provide an unique email address.");
        }
        RoleEntity role = roleRepository.findById(roleId).orElseThrow(() -> new RecordNotFoundException("Role with id: " + roleId + " not found"));
        userEntity.setRole(role);
        return userRepository.save(userEntity);
    }

    public List<UserEntity> getUsers() {
        return (List<UserEntity>) userRepository.findAll();
    }

    public UserEntity getUser(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("User with id: " + id + " not found"));
    }


    public UserEntity updateUser(Short roleId, Integer userId, UserEntity userEntity) {
        UserEntity returnValue = userRepository.findById(userId).orElseThrow(() -> new RecordNotFoundException("User with id: " + userId + " not found!"));
        if (userRepository.findByEmail(userEntity.getEmail()) != null && !userRepository.findByEmail(userEntity.getEmail()).getEmail().equals(returnValue.getEmail())) {
            throw new RecordNotFoundException("Record with email address: " + userEntity.getEmail() + " already exists! Please provide an unique email address.");
        }
        BeanUtils.copyProperties(userEntity, returnValue);
        returnValue.setId(userId);
        returnValue.setRole(roleRepository.findById(roleId).orElseThrow(() -> new RecordNotFoundException("Role with id: " + roleId + " not found")));
        return userRepository.save(returnValue);
    }

    public void deleteUser(Integer id) {
        userRepository.delete(userRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("User with id: " + id + " not found")));
    }

    public List<String> getEmailList() {
        List<String> returnValue = new ArrayList<>();
        List<UserEntity> users = (List<UserEntity>) userRepository.findAll();
        for (UserEntity user : users) {
            String email = user.getEmail();
            returnValue.add(email);
        }
        return returnValue;
    }

    public String getUserEmail(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("User with id: " + id + " not found")).getEmail();
    }
}
