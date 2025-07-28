package com.retail.erp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.retail.erp.model.UnitOfMeasurement;

public interface UomRepository extends JpaRepository<UnitOfMeasurement, Long>{
	
	boolean existsByUnitName(String unitName);

}
