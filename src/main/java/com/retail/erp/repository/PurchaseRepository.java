package com.retail.erp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.retail.erp.model.Purchase;

public interface PurchaseRepository extends JpaRepository<Purchase, Long>{

}
