package com.learn.spring.rest.api.exceptions;

public class InvoiceServiceException extends RuntimeException{

    public InvoiceServiceException(String message) {
        super(message);
    }
}
