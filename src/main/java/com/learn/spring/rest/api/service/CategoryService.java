package com.learn.spring.rest.api.service;

import com.learn.spring.rest.api.data.dao.CategoryEntity;
import com.learn.spring.rest.api.data.dto.CategoryDto;
import com.learn.spring.rest.api.exceptions.CategoryServiceException;
import com.learn.spring.rest.api.repository.CategoryRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    protected CategoryRepository categoryRepository;

    public CategoryDto createCategory(CategoryDto categoryDto) {
        CategoryDto returnValue = new CategoryDto();
        CategoryEntity category = new CategoryEntity();
        BeanUtils.copyProperties(categoryDto, category);
        CategoryEntity createdCategory = categoryRepository.save(category);
        BeanUtils.copyProperties(createdCategory, returnValue);
        categoryRepository.save(createdCategory);
        return returnValue;
    }

    public CategoryDto getCategory(Integer id) {
        CategoryDto returnValue = new CategoryDto();
        CategoryEntity category = categoryRepository.findById(id).orElseThrow(() -> new CategoryServiceException("Category with id: " + id + " not found"));
        BeanUtils.copyProperties(category, returnValue);
        return returnValue;
    }

    public List<CategoryDto> getCategories() {
        List<CategoryDto> returnValue = new ArrayList<>();
        List<CategoryEntity> categoryEntities = (List<CategoryEntity>) categoryRepository.findAll();
        for (CategoryEntity category : categoryEntities) {
            CategoryDto categoryDto = new CategoryDto();
            BeanUtils.copyProperties(category, categoryDto);
            returnValue.add(categoryDto);
        }
        return returnValue;
    }

    public CategoryDto updateCategory(Integer id, CategoryDto categoryDto) {
        CategoryDto returnValue = new CategoryDto();
        CategoryEntity category = categoryRepository.findById(id).orElseThrow(() -> new CategoryServiceException("Category with id: " + id + " not found"));
        BeanUtils.copyProperties(categoryDto, category);
        category.setId(id);
        BeanUtils.copyProperties(category, returnValue);
        categoryRepository.save(category);
        return returnValue;
    }

    public void deleteCategory(Integer id) {
        categoryRepository.delete(categoryRepository.findById(id).orElseThrow(() -> new CategoryServiceException("Category with id: " + id + " not found")));
    }
}
