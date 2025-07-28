package com.retail.erp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.retail.erp.model.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {
	
}
