package com.retail.erp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.retail.erp.model.Vendor;

public interface VendorRepository extends JpaRepository<Vendor, Long> {
	
}
