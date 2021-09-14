package com.learn.spring.rest.api.exceptions;

public class RoleServiceException extends RuntimeException {

    public RoleServiceException(String message) {
        super(message);
    }
}
