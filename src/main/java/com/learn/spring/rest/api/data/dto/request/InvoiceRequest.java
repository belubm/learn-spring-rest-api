package com.learn.spring.rest.api.data.dto.request;

public class InvoiceRequest {

    private String invoiceNumber;

    public InvoiceRequest() {

    }

    public InvoiceRequest(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }
}
