package com.learn.spring.rest.api.controller;

import com.learn.spring.rest.api.data.dto.CategoryDto;
import com.learn.spring.rest.api.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v01/categories")
public class CategoryController {

    @Autowired
    protected CategoryService categoryService;

    @PostMapping()
    public CategoryDto createCategory(@RequestBody CategoryDto categoryDto) {
        return categoryService.createCategory(categoryDto);
    }

    @GetMapping("/{id}")
    public CategoryDto getCategory(@PathVariable Integer id) {
        return categoryService.getCategory(id);
    }

    @GetMapping()
    public List<CategoryDto> getCategories() {
        return categoryService.getCategories();
    }

    @PutMapping("/{id}")
    public CategoryDto updateCategory(@PathVariable Integer id, @RequestBody CategoryDto categoryDto) {
        return categoryService.updateCategory(id, categoryDto);
    }

    @DeleteMapping("/{id}")
    public String deleteCategory(@PathVariable Integer id) {
        String categoryName = categoryService.getCategory(id).getName();
        categoryService.deleteCategory(id);
        return "Category with name: " + categoryName + " was permanently deleted";
    }
}
