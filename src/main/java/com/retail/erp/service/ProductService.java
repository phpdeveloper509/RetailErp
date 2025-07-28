package com.retail.erp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.retail.erp.model.Product;
import com.retail.erp.repository.ProductRepository;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Product getById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product create(Product product) {
        if (productRepository.existsByName(product.getName())) {
            throw new RuntimeException("Product already exists.");
        }
        return productRepository.save(product);
    }

    public Product update(Long id, Product updated) {
        Product product = getById(id);
        if (product == null) return null;

        product.setName(updated.getName());
        product.setCategory(updated.getCategory());
        product.setUnit(updated.getUnit());
        product.setPrice(updated.getPrice());
        product.setStock(updated.getStock());

        return productRepository.save(product);
    }

    public boolean delete(Long id) {
        if (!productRepository.existsById(id)) return false;
        productRepository.deleteById(id);
        return true;
    }
}
