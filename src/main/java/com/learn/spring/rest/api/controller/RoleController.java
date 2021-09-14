package com.learn.spring.rest.api.controller;

import com.learn.spring.rest.api.data.dao.RoleEntity;
import com.learn.spring.rest.api.data.dto.request.RoleRequest;
import com.learn.spring.rest.api.data.dto.response.RoleResponse;
import com.learn.spring.rest.api.service.RoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v01/roles")
public class RoleController {

    @Autowired
    protected RoleService roleService;

    @PostMapping()
    public RoleResponse createRole(@RequestBody RoleRequest roleRequest) {
        RoleResponse returnValue = new RoleResponse();
        RoleEntity roleEntity = new RoleEntity();
        BeanUtils.copyProperties(roleRequest, roleEntity);
        RoleEntity createdRole = roleService.createRole(roleEntity);
        BeanUtils.copyProperties(createdRole, returnValue);
        return returnValue;
    }

    @GetMapping("/{id}")
    public RoleResponse getRole(@PathVariable short id) {
        RoleResponse returnValue = new RoleResponse();
        RoleEntity role = roleService.getRole(id);
        BeanUtils.copyProperties(role, returnValue);
        return returnValue;
    }

    @GetMapping()
    public List<RoleResponse> getRoles() {
        List<RoleResponse> returnValue = new ArrayList<>();
        List<RoleEntity> roleList = roleService.getRoles();
        for (RoleEntity entity : roleList) {
            RoleResponse response = new RoleResponse();
            BeanUtils.copyProperties(entity, response);
            returnValue.add(response);
        }
        return returnValue;
    }

    @PutMapping("/{id}")
    public RoleResponse updateUser(@PathVariable short id, @RequestBody RoleRequest roleRequest) {
        RoleResponse returnValue = new RoleResponse();
        RoleEntity role = new RoleEntity();
        BeanUtils.copyProperties(roleRequest, role);
        RoleEntity updatedRole = roleService.updateUser(id, role);
        BeanUtils.copyProperties(updatedRole, returnValue);
        return returnValue;
    }

    @DeleteMapping("/{id}")
    public String deleteRole(@PathVariable short id) {
        roleService.deleteRole(id);
        return "Role with id: " + id + " was permanently deleted";
    }
}
