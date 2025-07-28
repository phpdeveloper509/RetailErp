package com.retail.erp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.retail.erp.model.SiteMaster;

public interface SiteMasterRepository extends JpaRepository<SiteMaster, Long> {
    boolean existsBySiteCode(String siteCode);
}
