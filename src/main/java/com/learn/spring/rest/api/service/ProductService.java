package com.learn.spring.rest.api.service;

import com.learn.spring.rest.api.data.dao.CategoryEntity;
import com.learn.spring.rest.api.data.dao.ProductEntity;
import com.learn.spring.rest.api.data.dto.CategoryDto;
import com.learn.spring.rest.api.data.dto.ProductDto;
import com.learn.spring.rest.api.exceptions.RecordNotFoundException;
import com.learn.spring.rest.api.repository.CategoryRepository;
import com.learn.spring.rest.api.repository.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    protected ProductRepository productRepository;

    @Autowired
    protected CategoryRepository categoryRepository;

    public ProductDto createProduct(Integer categoryId, ProductDto productDto) {

        CategoryEntity category = categoryRepository.findById(categoryId).orElseThrow(() -> new RecordNotFoundException("Category with id: " + categoryId + " not found"));
        ProductEntity productEntity = new ProductEntity();
        BeanUtils.copyProperties(productDto, productEntity);
        productEntity.setCategory(category);

        ProductEntity dbResponse = productRepository.save(productEntity);

        ProductDto returnValue = new ProductDto();
        BeanUtils.copyProperties(dbResponse, returnValue);
        CategoryDto finalCategory = new CategoryDto();
        BeanUtils.copyProperties(category, finalCategory);

        returnValue.setCategory(finalCategory);
        return returnValue;
    }

    public ProductDto getProduct(Long id) {
        ProductDto returnValue = new ProductDto();
        ProductEntity product = productRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Product with id: " + id + " not found"));
        CategoryDto categoryDto = new CategoryDto();
        BeanUtils.copyProperties(product.getCategory(), categoryDto);
        BeanUtils.copyProperties(product, returnValue);
        returnValue.setCategory(categoryDto);
        return returnValue;
    }

    public ProductDto updateProduct(Long id, ProductDto productDto) {
        ProductEntity product = productRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Product with id: " + id + " not found"));
        CategoryDto categoryDto = new CategoryDto();
        BeanUtils.copyProperties(product.getCategory(), categoryDto);
        productDto.setCategory(categoryDto);
        BeanUtils.copyProperties(productDto, product);
        productRepository.save(product);
        return productDto;
    }

    public void deleteProduct(Long id) {
        productRepository.delete(productRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Product with id: " + id + " not found")));
    }

    public List<ProductDto> getProducts() {
        List<ProductDto> returnValue = new ArrayList<>();
        List<ProductEntity> productEntities = (List<ProductEntity>) productRepository.findAll();
        for (ProductEntity product : productEntities) {
            ProductDto productDto = new ProductDto();
            CategoryDto categoryDto = new CategoryDto();
            BeanUtils.copyProperties(product, productDto);
            BeanUtils.copyProperties(product.getCategory(), categoryDto);
            productDto.setCategory(categoryDto);
            returnValue.add(productDto);
        }
        return returnValue;
    }
}
