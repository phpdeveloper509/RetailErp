package com.retail.erp.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.retail.erp.model.InventoryLog;
import com.retail.erp.model.Product;
import com.retail.erp.repository.InventoryLogRepository;
import com.retail.erp.repository.ProductRepository;

@Service
public class InventoryService {

    @Autowired
    private InventoryLogRepository logRepository;

    @Autowired
    private ProductRepository productRepository;

    public InventoryLog stockIn(Long productId, int qty, String remarks) {
        return recordMovement(productId, qty, "STOCK_IN", remarks);
    }

    public InventoryLog stockOut(Long productId, int qty, String remarks) {
        return recordMovement(productId, -qty, "STOCK_OUT", remarks);
    }

    public List<InventoryLog> history(Long productId) {
        return logRepository.findByProductId(productId);
    }

    private InventoryLog recordMovement(Long productId, int qty, String type, String remarks) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setStock(product.getStock() + qty);
        productRepository.save(product);

        InventoryLog log = InventoryLog.builder()
                .product(product)
                .quantity(qty)
                .type(type)
                .timestamp(new Date())
                .remarks(remarks)
                .build();

        return logRepository.save(log);
    }
}
