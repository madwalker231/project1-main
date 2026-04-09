package com.skillstormpg1.project1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillstormpg1.project1.Repository.BusinessEntityRepository;
import com.skillstormpg1.project1.models.BusinessEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/api/v1/entities")
@CrossOrigin(origins = "http://localhost:4200") // Preparing got future Angular frontend
public class BusinessEntityController {
    @Autowired
    private BusinessEntityRepository repository;

    // View all business entities
    @GetMapping()
    public List<BusinessEntity> getAllEntities() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public BusinessEntity getEntityById(@PathVariable Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Entity not Found."));
    }
    

    // Add business entities
    @PostMapping
    public BusinessEntity createEntity(@RequestBody BusinessEntity entity) {
        return repository.save(entity);
    }

    @PutMapping("/{id}")
    public BusinessEntity updateEntity(@PathVariable Long id, @RequestBody BusinessEntity entityDetails) {
        BusinessEntity entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Entity not found with ID: " + id));
        entity.setName(entityDetails.getName());
        entity.setEntityType(entityDetails.getEntityType());
        entity.setJurisdiction(entityDetails.getJurisdiction());
        entity.setStatus(entityDetails.getStatus());
        return repository.save(entity);
    }

    // Delete business entities
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEntity(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
