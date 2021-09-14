package com.learn.spring.rest.api.service;

import com.learn.spring.rest.api.data.dao.RoleEntity;
import com.learn.spring.rest.api.exceptions.RoleServiceException;
import com.learn.spring.rest.api.repository.RoleRepository;
import com.learn.spring.rest.api.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    protected RoleRepository roleRepository;

    @Autowired
    protected UserRepository userRepository;

    public RoleEntity createRole(RoleEntity roleEntity) {
        return roleRepository.save(roleEntity);
    }

    public RoleEntity getRole(Short id) {
        return roleRepository.findById(id).orElseThrow(() -> new RoleServiceException("Role with id: " + id + " not found"));
    }

    public List<RoleEntity> getRoles() {
        return (List<RoleEntity>) roleRepository.findAll();
    }

    public RoleEntity updateUser(Short id, @RequestBody RoleEntity roleEntity) {
        RoleEntity role = roleRepository.findById(id).orElseThrow(() -> new RoleServiceException("Role with id: " + id + " not found"));
        if (roleRepository.findByDescription(roleEntity.getDescription()) != null && !role.getDescription().equals(roleEntity.getDescription())) {
            throw new RoleServiceException("Role with description: " + roleEntity.getDescription() + " already exists! Please provide a unique role description.");
        }
        roleEntity.setId(id);
        BeanUtils.copyProperties(roleEntity, role);
        return roleRepository.save(role);
    }

    public void deleteRole(Short id) {
        roleRepository.delete(roleRepository.findById(id).orElseThrow(() -> new RoleServiceException("Role with id: " + id + " not found")));
    }
}
