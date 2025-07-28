package com.retail.erp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.retail.erp.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsByName(String name);
}
