package com.retail.erp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.retail.erp.model.Vendor;
import com.retail.erp.repository.VendorRepository;

@Service
public class VendorService {
	
	@Autowired
	private VendorRepository vendorRepository;
	
	public List<Vendor> getAllVendors() {
	    return vendorRepository.findAll();
	}

	public Vendor addVendor(Vendor vendor) {
	    return vendorRepository.save(vendor);
	}

	public Vendor getVendorById(Long id) {
	    return vendorRepository.findById(id).orElseThrow(() -> new RuntimeException("Vendor not found"));
	}

	public void deleteVendor(Long id) {
	    vendorRepository.deleteById(id);
	}


}
