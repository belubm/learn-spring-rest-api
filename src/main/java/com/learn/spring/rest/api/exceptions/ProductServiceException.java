package com.learn.spring.rest.api.exceptions;

public class ProductServiceException extends RuntimeException {

    public ProductServiceException(String message) {
        super(message);
    }
}
