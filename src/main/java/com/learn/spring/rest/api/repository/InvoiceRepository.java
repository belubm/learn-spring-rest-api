package com.learn.spring.rest.api.repository;

import com.learn.spring.rest.api.data.dao.InvoiceEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends CrudRepository<InvoiceEntity, Long> {
    InvoiceEntity findByInvoiceNumber(String invoiceNumber);
}
