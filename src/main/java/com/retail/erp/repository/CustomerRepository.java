package com.retail.erp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.retail.erp.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

}
