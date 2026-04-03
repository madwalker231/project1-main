package com.skillstormpg1.project1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillstormpg1.project1.Repository.BusinessEntityRepository;
import com.skillstormpg1.project1.models.BusinessEntity;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/v1/entities")
@CrossOrigin(origins = "http://localhost:4200") // Preparing got future Angular frontend
public class BusinessEntityController {
    @Autowired
    private BusinessEntityRepository repository;

    // View all business entities
    @GetMapping
    public List<BusinessEntity> getAllEntities() { 
        return repository.findAll();
    }

    // Add business entities
    @PostMapping
    public ResponseEntity<BusinessEntity> createEntity(@Valid @RequestBody BusinessEntity entity) {
        BusinessEntity savedEntity = repository.save(entity);
        return new ResponseEntity<>(savedEntity, HttpStatus.CREATED);
    }

    // Delete business entities
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEntity(@PathVariable Long id) {
        if(repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }    
}
