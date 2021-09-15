package com.learn.spring.rest.api.service;

import com.learn.spring.rest.api.data.dao.InvoiceEntity;
import com.learn.spring.rest.api.data.dao.UserEntity;
import com.learn.spring.rest.api.exceptions.DuplicateRecordException;
import com.learn.spring.rest.api.exceptions.RecordNotFoundException;
import com.learn.spring.rest.api.repository.InvoiceRepository;
import com.learn.spring.rest.api.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InvoiceService {

    @Autowired
    protected InvoiceRepository invoiceRepository;

    @Autowired
    protected UserRepository userRepository;

    public InvoiceEntity createInvoice(Integer userId, InvoiceEntity invoiceEntity) throws DuplicateRecordException {
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new RecordNotFoundException("User with id: " + userId + " not found"));
        invoiceEntity.setUser(user);
        if (invoiceRepository.findByInvoiceNumber(invoiceEntity.getInvoiceNumber()) != null) {
            throw new DuplicateRecordException("Invoice number " + invoiceEntity.getInvoiceNumber() + " already exists! Please provide a unique invoice number.");
        }
        return invoiceRepository.save(invoiceEntity);
    }

    public InvoiceEntity getInvoice(Long id) {
        return invoiceRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Invoice with id: " + id + " not found"));
    }

    public List<InvoiceEntity> getInvoices() {
        return (List<InvoiceEntity>) invoiceRepository.findAll();
    }

    public InvoiceEntity updateInvoice(Long id, InvoiceEntity invoiceEntity) throws DuplicateRecordException {
        InvoiceEntity invoice = invoiceRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Invoice with id: " + id + " not found"));
        if (invoiceRepository.findByInvoiceNumber(invoiceEntity.getInvoiceNumber()) != null && !invoiceEntity.getInvoiceNumber().equals(invoice.getInvoiceNumber())) {
            throw new DuplicateRecordException("Invoice with invoice number: " + invoiceEntity.getInvoiceNumber() + " already exists! Please provide a unique invoice number.");
        }
        invoiceEntity.setId(id);
        invoiceEntity.setUser(invoice.getUser());
        BeanUtils.copyProperties(invoiceEntity, invoice);
        return invoiceRepository.save(invoice);
    }

    public void deleteInvoice(Long id) {
        invoiceRepository.delete(invoiceRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Invoice with id: " + id + " not found")));
    }

    public List<InvoiceEntity> getUserInvoices(Integer userId) {
        List<InvoiceEntity> returnValue = new ArrayList<>();
        List<InvoiceEntity> invoices = (List<InvoiceEntity>) invoiceRepository.findAll();
        for (InvoiceEntity invoice : invoices) {
            if (invoice.getUser().getId().equals(userId)) {
                returnValue.add(invoice);
            }
        }
        return returnValue;
    }
}
