package com.retail.erp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.retail.erp.model.InventoryLog;

public interface InventoryLogRepository extends JpaRepository<InventoryLog, Long> {
    List<InventoryLog> findByProductId(Long productId);
}
