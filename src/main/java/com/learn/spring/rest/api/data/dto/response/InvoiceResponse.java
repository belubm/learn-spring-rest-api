package com.learn.spring.rest.api.data.dto.response;

import com.learn.spring.rest.api.data.dao.UserEntity;

public class InvoiceResponse {
    private Long id;
    private String invoiceNumber;
    private UserEntity user;

    public InvoiceResponse() {
    }

    public InvoiceResponse(Long id, String invoiceNumber, UserEntity user) {
        this.id = id;
        this.invoiceNumber = invoiceNumber;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
