package com.retail.erp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.retail.erp.model.Sale;
import com.retail.erp.service.SaleService;

@RestController
@RequestMapping("/api/sales")
public class SaleController {
    @Autowired private SaleService service;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Sale sale) {
        return ResponseEntity.ok(service.createSale(sale));
    }

    @GetMapping
    public List<Sale> list() {
        return service.getAll();
    }
}
