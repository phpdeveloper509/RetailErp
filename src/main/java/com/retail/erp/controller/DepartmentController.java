package com.retail.erp.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.retail.erp.model.Department;
import com.retail.erp.service.DepartmentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/departments")
@CrossOrigin(origins = "http://localhost:3000")
public class DepartmentController {

    @Autowired
    private DepartmentService service;

    @GetMapping
    public List<Department> list() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<Department> create(@Valid @RequestBody Department dept) {
        return ResponseEntity.ok(service.save(dept));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Department> update(@PathVariable("id") Long id, @Valid @RequestBody Department updated) {
        Department existing = service.getById(id);
        existing.setName(updated.getName());
        existing.setDescription(updated.getDescription());
        existing.setEmail(updated.getEmail());
        existing.setTelephone(updated.getTelephone());
        existing.setFax(updated.getFax());
        existing.setDeptHead(updated.getDeptHead());
        return ResponseEntity.ok(service.save(existing));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
