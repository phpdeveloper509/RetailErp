package com.retail.erp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.retail.erp.model.LineItem;

public interface LineItemRepository extends JpaRepository<LineItem, Long> {

}
