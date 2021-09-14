package com.learn.spring.rest.api.repository;

import com.learn.spring.rest.api.data.dao.CategoryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<CategoryEntity, Integer> {
}
