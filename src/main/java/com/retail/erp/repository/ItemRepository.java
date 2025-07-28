package com.retail.erp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.retail.erp.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
    boolean existsByItemCode(String itemCode);
}