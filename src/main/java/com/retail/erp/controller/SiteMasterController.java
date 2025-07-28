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

import com.retail.erp.model.SiteMaster;
import com.retail.erp.repository.SiteMasterRepository;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/sites")
public class SiteMasterController {

    @Autowired
    private SiteMasterRepository siteRepo;

    @GetMapping
    public List<SiteMaster> listSites() {
        return siteRepo.findAll();
    }

    @PostMapping
    public ResponseEntity<?> createSite(@Valid @RequestBody SiteMaster site) {
        if (siteRepo.existsBySiteCode(site.getSiteCode())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Site code already exists");
        }
        return ResponseEntity.ok(siteRepo.save(site));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSite(@PathVariable("id") Long id, @Valid @RequestBody SiteMaster site) {
        return siteRepo.findById(id)
                .map(existing -> {
                    existing.setSiteCode(site.getSiteCode());
                    existing.setSiteName(site.getSiteName());
                    existing.setContactPerson(site.getContactPerson());
                    existing.setEmail(site.getEmail());
                    existing.setPhone(site.getPhone());
                    return ResponseEntity.ok(siteRepo.save(existing));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSite(@PathVariable("id") Long id) {
        return siteRepo.findById(id)
                .map(s -> {
                    siteRepo.delete(s);
                    return ResponseEntity.ok("Deleted");
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSite(@PathVariable("id") Long id) {
        return siteRepo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
