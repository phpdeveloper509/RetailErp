package com.retail.erp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.retail.erp.model.LineItem;
import com.retail.erp.model.Product;
import com.retail.erp.model.Purchase;
import com.retail.erp.repository.ProductRepository;
import com.retail.erp.repository.PurchaseRepository;

@Service
public class PurchaseService {

    @Autowired private PurchaseRepository purchaseRepo;
    @Autowired private ProductRepository productRepo;
    @Autowired private InventoryService inventoryService;

    public Purchase createPurchase(Purchase purchase) {
        double total = 0;

        for (LineItem item : purchase.getItems()) {
            Product product = productRepo.findById(item.getProduct().getId())
                .orElseThrow(() -> new RuntimeException("Product not found"));
            
            inventoryService.stockIn(product.getId(), item.getQuantity(), "Purchase Bill");
            item.setPurchase(purchase);
            item.setRate(product.getPrice());
            total += item.getRate() * item.getQuantity();
        }

        purchase.setTotalAmount(total);
        return purchaseRepo.save(purchase);
    }

    public List<Purchase> getAll() {
        return purchaseRepo.findAll();
    }
}

