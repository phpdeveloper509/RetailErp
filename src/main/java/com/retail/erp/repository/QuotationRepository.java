package com.retail.erp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.retail.erp.model.Quotation;

public interface QuotationRepository extends JpaRepository<Quotation, Long> {
}
