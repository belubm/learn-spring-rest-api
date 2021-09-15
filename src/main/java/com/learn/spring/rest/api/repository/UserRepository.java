package com.learn.spring.rest.api.repository;

import com.learn.spring.rest.api.data.dao.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    UserEntity findByEmail(String email);
    UserEntity getUserById(Integer id);
}
