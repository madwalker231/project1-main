package com.skillstormpg1.project1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillstormpg1.project1.Repository.RegulatoryEntityRepository;
import com.skillstormpg1.project1.models.RegulatoryEntity;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;






@RestController
@RequestMapping("/api/v1/regulations")
@CrossOrigin(origins = "http://localhost:4200") // Preparing got future Angular frontend
public class RegulatoryController {
    @Autowired
    private RegulatoryEntityRepository repository;

    // View all regulations
    @GetMapping
    public List<RegulatoryEntity> getAllRegulations() {
        return repository.findAll();
    }

    @GetMapping("/search")
    public List<RegulatoryEntity> searchRegulatoryEntities(
        @RequestParam (required = false) String name,
        @RequestParam (required = false) String body,
        @RequestParam (required = false) String status) {
        if (name != null) {
            return repository.findByNameContainingIgnoreCase(name);
        } else if (body != null) {
            return repository.findByGoverningBody(body);
        } else if (status != null) {
            return repository.findByStatus(status);
        }
        return repository.findAll();
    }
    

    // Add Regulatory Entities
    @PostMapping
    public ResponseEntity<RegulatoryEntity> createRegulatory(@Valid @RequestBody RegulatoryEntity entity) {
        RegulatoryEntity savedReg = repository.save(entity);
        return new ResponseEntity<>(savedReg, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RegulatoryEntity> updateRequirements(@PathVariable Long id, @RequestBody RegulatoryEntity details) {
        return repository.findById(id).map(requirement -> {
            requirement.setName(details.getName());
            requirement.setGoverningBody(details.getGoverningBody());
            requirement.setRequirementType(details.getRequirementType());
            requirement.setEffectiveDate(details.getEffectiveDate());
            requirement.setStatus(details.getStatus());

            RegulatoryEntity update = repository.save(requirement);
            return ResponseEntity.ok(update);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReg(@PathVariable Long id) {
        if(repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }    
}
