package com.learn.spring.rest.api.exceptions;

public class CategoryServiceException extends RuntimeException {

    public CategoryServiceException(String message) {
        super(message);
    }
}
