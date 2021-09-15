package com.learn.spring.rest.api.controller;

import com.learn.spring.rest.api.data.dao.InvoiceEntity;
import com.learn.spring.rest.api.data.dto.request.InvoiceRequest;
import com.learn.spring.rest.api.data.dto.response.InvoiceResponse;
import com.learn.spring.rest.api.exceptions.DuplicateRecordException;
import com.learn.spring.rest.api.service.InvoiceService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v01")
public class InvoiceController {

    @Autowired
    protected InvoiceService invoiceService;

    @PostMapping("/users/{userId}/invoices")
    public InvoiceResponse createInvoice(@PathVariable Integer userId, @RequestBody InvoiceRequest invoiceRequest) throws DuplicateRecordException {
        InvoiceResponse returnValue = new InvoiceResponse();
        InvoiceEntity invoiceEntity = new InvoiceEntity();
        BeanUtils.copyProperties(invoiceRequest, invoiceEntity);
        InvoiceEntity createdInvoice = invoiceService.createInvoice(userId, invoiceEntity);
        BeanUtils.copyProperties(createdInvoice, returnValue);
        return returnValue;
    }


    @GetMapping("/invoices/{id}")
    public InvoiceResponse getInvoice(@PathVariable Long id) {
        InvoiceResponse returnValue = new InvoiceResponse();
        InvoiceEntity invoice = invoiceService.getInvoice(id);
        BeanUtils.copyProperties(invoice, returnValue);
        return returnValue;
    }

    @GetMapping("/invoices")
    public List<InvoiceResponse> getInvoices() {
        List<InvoiceResponse> returnValue = new ArrayList<>();
        List<InvoiceEntity> invoices = invoiceService.getInvoices();
        for (InvoiceEntity invoice : invoices) {
            InvoiceResponse invoiceResponse = new InvoiceResponse();
            BeanUtils.copyProperties(invoice, invoiceResponse);
            returnValue.add(invoiceResponse);
        }
        return returnValue;
    }

    @PutMapping("/invoices/{id}")
    public InvoiceResponse updateInvoice(@PathVariable Long id, @RequestBody InvoiceRequest invoiceRequest) throws DuplicateRecordException {
        InvoiceResponse returnValue = new InvoiceResponse();
        InvoiceEntity invoiceEntity = new InvoiceEntity();
        BeanUtils.copyProperties(invoiceRequest, invoiceEntity);
        InvoiceEntity createdInvoice = invoiceService.updateInvoice(id, invoiceEntity);
        BeanUtils.copyProperties(createdInvoice, returnValue);
        return returnValue;
    }

    @DeleteMapping("/invoices/{id}")
    public String deleteInvoice(@PathVariable Long id) {
        invoiceService.deleteInvoice(id);
        return "Invoice with id: " + id + " was permanently deleted";
    }

    @GetMapping("/users/{userId}/invoices")
    public List<InvoiceResponse> getUserInvoices(@PathVariable Integer userId) {
        List<InvoiceResponse> returnValue = new ArrayList<>();
        List<InvoiceEntity> invoices = invoiceService.getUserInvoices(userId);
        for (InvoiceEntity invoice : invoices) {
            InvoiceResponse invoiceResponse = new InvoiceResponse();
            BeanUtils.copyProperties(invoice, invoiceResponse);
            returnValue.add(invoiceResponse);
        }
        return returnValue;
    }
}
