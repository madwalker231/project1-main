package com.skillstormpg1.project1.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillstormpg1.project1.Repository.ComplianceObligationRepository;
import com.skillstormpg1.project1.models.ComplianceObligation;

@Service
public class ComplianceObligationService {
    @Autowired
    private ComplianceObligationRepository obligationRepo;
    
    public ComplianceObligation savedComplianceObligation(ComplianceObligation obligation) {
        Optional<ComplianceObligation> existing = obligationRepo.findByEntityAndRequirements(obligation.getEntity(), obligation.getRequirements());

        if (existing.isPresent()) {
            ComplianceObligation found = existing.get();

            // Updates only if the entity status is different
            if(!found.getStatus().equalsIgnoreCase(obligation.getStatus())) {
                found.setStatus(obligation.getStatus());
                return obligationRepo.save(found);
            }
            return found; // Returns existing if no change
        }
        return obligationRepo.save(obligation);
    }
}
