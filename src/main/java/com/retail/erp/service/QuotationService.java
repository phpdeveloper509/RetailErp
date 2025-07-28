package com.retail.erp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.retail.erp.model.Quotation;
import com.retail.erp.repository.QuotationRepository;

@Service
public class QuotationService {

    private final QuotationRepository quotationRepository;

    public QuotationService(QuotationRepository quotationRepository) {
        this.quotationRepository = quotationRepository;
    }

    public List<Quotation> getAll() {
        return quotationRepository.findAll();
    }

    public Quotation save(Quotation quotation) {
        quotation.getLineItems().forEach(item -> item.setQuotation(quotation));
        return quotationRepository.save(quotation);
    }

    public Quotation getById(Long id) {
        return quotationRepository.findById(id).orElseThrow();
    }

    public void delete(Long id) {
        quotationRepository.deleteById(id);
    }
    
	/*
	 * public List<Quotation> getFilteredQuotations(String status, Long vendorId,
	 * LocalDate fromDate, LocalDate toDate) { return
	 * quotationRepository.findFiltered(status, vendorId, fromDate, toDate); }
	 */
}
