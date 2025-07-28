package com.retail.erp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.retail.erp.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long>{

}
