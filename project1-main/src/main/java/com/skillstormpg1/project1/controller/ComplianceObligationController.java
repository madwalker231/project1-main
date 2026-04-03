package com.skillstormpg1.project1.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillstormpg1.project1.Repository.BusinessEntityRepository;
import com.skillstormpg1.project1.Repository.ComplianceObligationRepository;
import com.skillstormpg1.project1.Repository.RegulatoryEntityRepository;
import com.skillstormpg1.project1.models.BusinessEntity;
import com.skillstormpg1.project1.models.ComplianceObligation;
import com.skillstormpg1.project1.models.RegulatoryEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/api/v1/obligations")
@CrossOrigin(origins = "http://localhost:4200")
public class ComplianceObligationController {
    @Autowired
    private ComplianceObligationRepository obligationRepo;
    
    @Autowired 
    private BusinessEntityRepository entityRepo;

    @Autowired
    private RegulatoryEntityRepository reqRepo; // Re-funnel through Service layer

    @GetMapping
    public List<ComplianceObligation> getAll() {
        return obligationRepo.findAll();
    }

    @PostMapping
    public ResponseEntity<?> assignRequirement(@RequestBody ComplianceObligation obligation) {
        BusinessEntity entity = entityRepo.findById(obligation.getEntity().getId()).orElse(null);
        RegulatoryEntity req = reqRepo.findById(obligation.getRequirements().getId()).orElse(null);
        // Checks for business and regulatory entities
        if(entity == null || req == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entity or Requirement not found.");
        }
        if ("Superseded".equalsIgnoreCase(req.getStatus())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Cannot assign a requirement with 'Superseded' status.");
        }
        // Queries database to check if obligation already exists
        Optional<ComplianceObligation> existingOpt = obligationRepo.findByEntityAndRequirements(entity, req);
        // If obligation exists checks obligations status changes and updates the obligation
        if(existingOpt.isPresent()) {
            ComplianceObligation existing = existingOpt.get();
            if(!existing.getStatus().equalsIgnoreCase(obligation.getStatus())) {
                existing.setStatus(obligation.getStatus());
                existing.setLastReviewDate(obligation.getLastReviewDate());
                existing.setNotes(obligation.getNotes());
                ComplianceObligation updated = obligationRepo.save(existing);
                return ResponseEntity.ok(updated);
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Duplicate obligation detected: This entity is already assigned this requirement with the same status.");
                // If obligation exists and status is the same than does not allow the update to happen
            }
        }
        // Saves the obligation as nee obligation.
        obligation.setEntity(entity);
        obligation.setRequirements(req);
        ComplianceObligation saved = obligationRepo.save(obligation);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ComplianceObligation> updateObligation(@PathVariable Long id, @RequestBody ComplianceObligation details) {
        return obligationRepo.findById(id).map(obligation -> {
            obligation.setStatus(details.getStatus());
            obligation.setLastReviewDate(details.getLastReviewDate());
            obligation.setNotes(details.getNotes());
            return ResponseEntity.ok(obligationRepo.save(obligation));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeObligation(@PathVariable Long id) {
        if (obligationRepo.existsById(id)) {
            obligationRepo.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
