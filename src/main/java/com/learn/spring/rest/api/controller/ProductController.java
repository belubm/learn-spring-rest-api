package com.learn.spring.rest.api.controller;

import com.learn.spring.rest.api.data.dto.ProductDto;
import com.learn.spring.rest.api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v01/")
public class ProductController {

    @Autowired
    protected ProductService productService;

    @PostMapping("categories/{categoryId}/products")
    public ProductDto createProduct(@PathVariable Integer categoryId, @RequestBody ProductDto productDto) {
        return productService.createProduct(categoryId, productDto);
    }

    @GetMapping("/products/{id}")
    public ProductDto getProduct(@PathVariable Long id) {
        return productService.getProduct(id);
    }

    @GetMapping("/products")
    public List<ProductDto> getProducts() {
        return productService.getProducts();
    }

    @PutMapping("/products/{id}")
    public ProductDto updateProduct(@PathVariable Long id, @RequestBody ProductDto productDto) {
        return productService.updateProduct(id, productDto);
    }

    @DeleteMapping("/products/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "Product with id: " + id + " was permanently deleted";
    }
}
