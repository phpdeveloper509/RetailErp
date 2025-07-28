package com.retail.erp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.retail.erp.model.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Long>{
	
	boolean existsBySuppCode(String suppCode);

}
