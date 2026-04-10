package com.skillstormpg1.project1.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.skillstormpg1.project1.Repository.RegulatoryEntityRepository;
import com.skillstormpg1.project1.models.RegulatoryEntity;

@RestController
@RequestMapping("/api/v1/requirements")
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
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String body,
            @RequestParam(required = false) String status) {
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

    @PostMapping(consumes = { "application/json", "application/json;charset=UTF-8" })
    public ResponseEntity<RegulatoryEntity> create(@RequestBody RegulatoryEntity entity) {
        System.out.println("Received Entity: " + entity.getName()); // Diagnostic log
        return ResponseEntity.ok(repository.save(entity));
    }

    @PutMapping("/{id}")
    public RegulatoryEntity updateRegulatory(@PathVariable Long id, @RequestBody RegulatoryEntity requirementDetails) {
        RegulatoryEntity requirement = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Requirement not found"));

        requirement.setName(requirementDetails.getName());
        requirement.setGoverningBody(requirementDetails.getGoverningBody());
        requirement.setRequirementType(requirementDetails.getRequirementType());
        requirement.setEffectiveDate(requirementDetails.getEffectiveDate());
        requirement.setStatus(requirementDetails.getStatus());

        return repository.save(requirement);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReg(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
