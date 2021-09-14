package com.learn.spring.rest.api.repository;

import com.learn.spring.rest.api.data.dao.RoleEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<RoleEntity, Short> {
    RoleEntity findByDescription(String description);
}
