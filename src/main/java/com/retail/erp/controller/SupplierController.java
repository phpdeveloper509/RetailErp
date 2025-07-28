package com.retail.erp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.retail.erp.model.Supplier;
import com.retail.erp.repository.SupplierRepository;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/suppliers")
public class SupplierController {
	
	@Autowired
	private SupplierRepository supplierRepo;
	
	@GetMapping
    public List<Supplier> listSites() {
        return supplierRepo.findAll();
    }
	
	@PostMapping
    public ResponseEntity<?> createSupplier(@Valid @RequestBody Supplier supplier) {
        if (supplierRepo.existsBySuppCode(supplier.getSuppCode())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Site code already exists");
        }
        return ResponseEntity.ok(supplierRepo.save(supplier));
    }

	@PutMapping("/{id}")
	public ResponseEntity<?> updateSupplier(@PathVariable("id") Long id, @Valid @RequestBody Supplier supplier) {
	    return supplierRepo.findById(id)
	            .map(existing -> {
	                existing.setSuppCode(supplier.getSuppCode());
	                existing.setSuppName(supplier.getSuppName());
	                existing.setContactPerson(supplier.getContactPerson());
	                existing.setEmail(supplier.getEmail());
	                existing.setPhone(supplier.getPhone());
	                existing.setCountry(supplier.getCountry());
	                return ResponseEntity.ok(supplierRepo.save(existing));
	            })
	            .orElse(ResponseEntity.notFound().build());
	}

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSite(@PathVariable("id") Long id) {
        return supplierRepo.findById(id)
                .map(s -> {
                    supplierRepo.delete(s);
                    return ResponseEntity.ok("Deleted");
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSite(@PathVariable("id") Long id) {
        return supplierRepo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
