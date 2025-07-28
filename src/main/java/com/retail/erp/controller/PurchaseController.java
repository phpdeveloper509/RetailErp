package com.retail.erp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.retail.erp.model.Purchase;
import com.retail.erp.service.PurchaseService;

@RestController
@RequestMapping("/api/purchases")
public class PurchaseController {
    @Autowired private PurchaseService service;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Purchase purchase) {
        return ResponseEntity.ok(service.createPurchase(purchase));
    }

    @GetMapping
    public List<Purchase> list() {
        return service.getAll();
    }
}

