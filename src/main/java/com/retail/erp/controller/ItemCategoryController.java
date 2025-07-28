package com.retail.erp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.retail.erp.model.ItemCategory;
import com.retail.erp.repository.ItemCategoryRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/itemcategories")
@CrossOrigin(origins = "http://localhost:3000")
public class ItemCategoryController {

    @Autowired
    private ItemCategoryRepository repo;

    @GetMapping
    public List<ItemCategory> getAll() {
    	System.out.println("we are here");
        return repo.findAll();
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody ItemCategory category) {
        return ResponseEntity.ok(repo.save(category));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @Valid @RequestBody ItemCategory category) {
        category.setId(id);
        return ResponseEntity.ok(repo.save(category));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        repo.deleteById(id);
        return ResponseEntity.ok().build();
    }
}