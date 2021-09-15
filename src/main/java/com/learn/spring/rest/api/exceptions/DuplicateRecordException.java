package com.learn.spring.rest.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.sql.SQLIntegrityConstraintViolationException;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DuplicateRecordException extends SQLIntegrityConstraintViolationException {

    public DuplicateRecordException(String message) {
        super(message);
    }
}
