package com.retail.erp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.retail.erp.model.InventoryLog;
import com.retail.erp.service.InventoryService;

import lombok.Data;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @PostMapping("/stock-in")
    public ResponseEntity<?> stockIn(@RequestBody InventoryRequest req) {
        return ResponseEntity.ok(inventoryService.stockIn(req.getProductId(), req.getQuantity(), req.getRemarks()));
    }

    @PostMapping("/stock-out")
    public ResponseEntity<?> stockOut(@RequestBody InventoryRequest req) {
        return ResponseEntity.ok(inventoryService.stockOut(req.getProductId(), req.getQuantity(), req.getRemarks()));
    }

    @GetMapping("/history/{productId}")
    public List<InventoryLog> history(@PathVariable Long productId) {
        return inventoryService.history(productId);
    }

    @Data
    static class InventoryRequest {
        private Long productId;
        private int quantity;
        private String remarks;
    }
}
