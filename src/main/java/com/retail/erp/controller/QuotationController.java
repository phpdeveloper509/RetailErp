package com.retail.erp.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.retail.erp.dto.QuotationDTO;
import com.retail.erp.model.Quotation;
import com.retail.erp.model.QuotationItem;
import com.retail.erp.repository.ItemRepository;
import com.retail.erp.repository.QuotationRepository;
import com.retail.erp.repository.SupplierRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/quotations")
@RequiredArgsConstructor
public class QuotationController {

    private final QuotationRepository quotationRepo;
    private final SupplierRepository supplierRepo;
    private final ItemRepository itemRepo;

    @PostMapping
    public ResponseEntity<Quotation> create(@RequestBody QuotationDTO dto) {
        Quotation q = new Quotation();
        mapQuotation(dto, q);
        return ResponseEntity.ok(quotationRepo.save(q));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Quotation> update(@PathVariable Long id, @RequestBody QuotationDTO dto) {
        Quotation q = quotationRepo.findById(id).orElseThrow();
        q.getLineItems().clear();
        mapQuotation(dto, q);
        return ResponseEntity.ok(quotationRepo.save(q));
    }

    @GetMapping
    public List<Quotation> getAll() {
        return quotationRepo.findAll();
    }

    @GetMapping("/{id}")
    public Quotation getOne(@PathVariable Long id) {
        return quotationRepo.findById(id).orElseThrow();
    }

    private void mapQuotation(QuotationDTO dto, Quotation q) {
        q.setQuotationNumber(dto.getQuotationNumber());
        q.setQuotationDate(dto.getQuotationDate());
        q.setStatus(dto.getStatus());
        q.setRemarks(dto.getRemarks());
        q.setSupplier(supplierRepo.findById(dto.getSupplierId()).orElseThrow());

        for (QuotationDTO.QuotationItemDTO itemDto : dto.getItems()) {
            QuotationItem qi = new QuotationItem();
            qi.setItem(itemRepo.findById(itemDto.getItemId()).orElseThrow());
            qi.setQuantity(itemDto.getQuantity());
            qi.setRate(itemDto.getRate());
            qi.setTax(itemDto.getTax());
            qi.setQuotation(q);
            q.getLineItems().add(qi);
        }
    }
}
