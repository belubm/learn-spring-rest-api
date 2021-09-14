package com.learn.spring.rest.api.data.dto;

public class ProductDto {

    private String name;
    private CategoryDto category;

    public ProductDto() {
    }

    public ProductDto(String name, CategoryDto category) {
        this.name = name;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryDto getCategory() {
        return category;
    }

    public void setCategory(CategoryDto category) {
        this.category = category;
    }
}
