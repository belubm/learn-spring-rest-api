package com.learn.spring.rest.api.service;

import com.learn.spring.rest.api.data.dao.RoleEntity;
import com.learn.spring.rest.api.exceptions.DuplicateRecordException;
import com.learn.spring.rest.api.exceptions.RecordNotFoundException;
import com.learn.spring.rest.api.repository.RoleRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    protected RoleRepository roleRepository;

    public RoleEntity createRole(RoleEntity roleEntity) throws DuplicateRecordException {
        if (roleRepository.findByDescription(roleEntity.getDescription()) != null) {
            throw new DuplicateRecordException("Role with description: " + roleEntity.getDescription() + " already exists! Please define a unique role.");
        }
        return roleRepository.save(roleEntity);
    }

    public RoleEntity getRole(Short id) {
        return roleRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Role with id: " + id + " not found"));
    }

    public List<RoleEntity> getRoles() {
        return (List<RoleEntity>) roleRepository.findAll();
    }

    public RoleEntity updateUser(Short id, @RequestBody RoleEntity roleEntity) throws DuplicateRecordException {
        RoleEntity role = roleRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Role with id: " + id + " not found"));
        if (roleRepository.findByDescription(roleEntity.getDescription()) != null && !role.getDescription().equals(roleEntity.getDescription())) {
            throw new DuplicateRecordException("Role with description: " + roleEntity.getDescription() + " already exists! Please provide a unique role description.");
        }
        roleEntity.setId(id);
        BeanUtils.copyProperties(roleEntity, role);
        return roleRepository.save(role);
    }

    public void deleteRole(Short id) {
        roleRepository.delete(roleRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Role with id: " + id + " not found")));
    }
}
