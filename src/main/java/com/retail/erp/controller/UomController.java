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

import com.retail.erp.model.UnitOfMeasurement;
import com.retail.erp.repository.UomRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/units")
@CrossOrigin(origins = "http://localhost:3000")
public class UomController {
	@Autowired
	private UomRepository uomRepository;
	
	@GetMapping
    public List<UnitOfMeasurement> listSites() {
        return uomRepository.findAll();
    }
	
	@PostMapping
    public ResponseEntity<?> createSupplier(@Valid @RequestBody UnitOfMeasurement uom) {
        if (uomRepository.existsByUnitName(uom.getUnitName())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Unit already exists");
        }
        return ResponseEntity.ok(uomRepository.save(uom));
    }

	@PutMapping("/{id}")
	public ResponseEntity<?> updateSupplier(@PathVariable("id") Long id, @Valid @RequestBody UnitOfMeasurement uom) {
	    return uomRepository.findById(id)
	            .map(existing -> {
	                existing.setUnitName(uom.getUnitName());
	                existing.setUnitDescription(uom.getUnitDescription());
	                return ResponseEntity.ok(uomRepository.save(existing));
	            })
	            .orElse(ResponseEntity.notFound().build());
	}

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSite(@PathVariable("id") Long id) {
        return uomRepository.findById(id)
                .map(s -> {
                    uomRepository.delete(s);
                    return ResponseEntity.ok("Deleted");
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSite(@PathVariable("id") Long id) {
        return uomRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
