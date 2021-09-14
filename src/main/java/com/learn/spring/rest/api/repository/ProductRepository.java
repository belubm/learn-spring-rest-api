package com.learn.spring.rest.api.repository;

import com.learn.spring.rest.api.data.dao.ProductEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<ProductEntity, Long> {
}
