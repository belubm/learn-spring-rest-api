package com.learn.spring.rest.api.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ControllerAdvice
public class AppExceptionsHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
    public final ResponseEntity<Object> handleAllExceptions(Exception exception, WebRequest webRequest) {
        List<String> details = new ArrayList<>();
        details.add(exception.getLocalizedMessage());
        ErrorMessage errorMessage = new ErrorMessage(new Date(), "Server Error", details);
        return new ResponseEntity<Object>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {RecordNotFoundException.class})
    public final ResponseEntity<Object> handleRecordNotFoundException(RecordNotFoundException exception, WebRequest webRequest) {
        List<String> details = new ArrayList<>();
        details.add(exception.getLocalizedMessage());
        ErrorMessage errorMessage = new ErrorMessage(new Date(), "Record Not Found", details);
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {DuplicateRecordException.class})
    public final ResponseEntity<Object> handleDuplicateRecordException(DuplicateRecordException exception, WebRequest webRequest) {
        List<String> details = new ArrayList<>();
        details.add(exception.getLocalizedMessage());
        ErrorMessage errorMessage = new ErrorMessage(new Date(), "Duplicate Record", details);
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }


    //    @ExceptionHandler(value = {UserServiceException.class})
    //    public ResponseEntity<Object> userServiceException(UserServiceException exception, WebRequest webRequest) {
    //        ErrorMessage errorMessage = new ErrorMessage(new Date(), exception.getMessage());
    //        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    //    }
    //
    //    @ExceptionHandler(value = {RoleServiceException.class})
    //    public ResponseEntity<Object> roleServiceException(RoleServiceException exception, WebRequest webRequest) {
    //        ErrorMessage errorMessage = new ErrorMessage(new Date(), exception.getMessage());
    //        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    //    }
    //
    //    @ExceptionHandler(value = {ProductServiceException.class})
    //    public ResponseEntity<Object> productServiceException(ProductServiceException exception, WebRequest webRequest) {
    //        ErrorMessage errorMessage = new ErrorMessage(new Date(), exception.getMessage());
    //        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    //    }
    //
    //    @ExceptionHandler(value = {InvoiceServiceException.class})
    //    public ResponseEntity<Object> invoiceServiceException(InvoiceServiceException exception, WebRequest webRequest) {
    //        ErrorMessage errorMessage = new ErrorMessage(new Date(), exception.getMessage());
    //        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    //    }
    //
    //    @ExceptionHandler(value = {CategoryServiceException.class})
    //    public ResponseEntity<Object> categoryServiceException(CategoryServiceException exception, WebRequest webRequest) {
    //        ErrorMessage errorMessage = new ErrorMessage(new Date(), exception.getMessage());
    //        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    //    }
}
