package com.retail.erp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.retail.erp.model.LineItem;
import com.retail.erp.model.Product;
import com.retail.erp.model.Sale;
import com.retail.erp.repository.ProductRepository;
import com.retail.erp.repository.SaleRepository;

@Service
public class SaleService {

    @Autowired private SaleRepository saleRepo;
    @Autowired private ProductRepository productRepo;
    @Autowired private InventoryService inventoryService;

    public Sale createSale(Sale sale) {
        double total = 0;

        for (LineItem item : sale.getItems()) {
            Product product = productRepo.findById(item.getProduct().getId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

            if (product.getStock() < item.getQuantity()) {
                throw new RuntimeException("Not enough stock for product: " + product.getName());
            }

            inventoryService.stockOut(product.getId(), item.getQuantity(), "Customer Sale");
            item.setSale(sale);
            item.setRate(product.getPrice());
            total += item.getRate() * item.getQuantity();
        }

        sale.setTotalAmount(total);
        return saleRepo.save(sale);
    }

    public List<Sale> getAll() {
        return saleRepo.findAll();
    }
}
