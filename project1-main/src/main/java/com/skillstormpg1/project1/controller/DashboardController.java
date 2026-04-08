package com.skillstormpg1.project1.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillstormpg1.project1.Repository.BusinessEntityRepository;
import com.skillstormpg1.project1.Repository.ComplianceObligationRepository;
import com.skillstormpg1.project1.Repository.RegulatoryEntityRepository;
import com.skillstormpg1.project1.models.ComplianceAlertDTO;
import com.skillstormpg1.project1.models.ComplianceObligation;
import com.skillstormpg1.project1.models.DashboardDTO;
import com.skillstormpg1.project1.models.RegulatoryEntity;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/v1/dashboard")
@CrossOrigin(origins = "http://localhost:4200") // Future proofing for Angular frontend
public class DashboardController {
    @Autowired
    private BusinessEntityRepository entityRepo;

    @Autowired
    private RegulatoryEntityRepository reqRepo;

    @Autowired
    private ComplianceObligationRepository obligationRepo;

    @GetMapping("/stats")
    public DashboardDTO getDashboardStats() {
        LocalDate today = LocalDate.now();
        LocalDate sixtyDaysOut = today.plusDays(60);

        // 1. Fetch raw counts
        long totalEntities = entityRepo.count();
        long totalReqs = reqRepo.count();
        long nonCompliantCount = obligationRepo.countByStatusIgnoreCase("Non-Compliant");

        // 2. Fetch upcoming requirements
        List<RegulatoryEntity> upcoming = reqRepo.findByEffectiveDateBetween(today, sixtyDaysOut);

        // 3. STEP C: Fetch raw obligations and TRANSLATE them to DTOs
        // Fetch the raw database objects first
        List<ComplianceObligation> rawObligations = obligationRepo.findByStatusIgnoreCase("Non-Compliant");

        // Translate them into the flattened DTOs the frontend can read
        List<ComplianceAlertDTO> critical = rawObligations.stream()
                .map(ob -> new ComplianceAlertDTO(
                        ob.getId(),
                        ob.getEntity().getName(), // Extracts the Bank name
                        ob.getRequirement().getName() // Extracts the Rule name
                ))
                .collect(java.util.stream.Collectors.toList());

        // 4. Calculate Score
        long totalObligations = obligationRepo.count();
        long compliantCount = obligationRepo.countByStatusIgnoreCase("Compliant");
        double score = (totalObligations > 0) ? ((double) compliantCount / totalObligations) * 100 : 0;

        // 5. Return the finished Dashboard object
        return DashboardDTO.builder()
                .totalEntities(totalEntities)
                .totalRequirements(totalReqs)
                .nonCompliantCount(nonCompliantCount)
                .upcomingRequirements(upcoming)
                .criticalObligations(critical)
                .overallComplianceScore(score)
                .build();
    }
}
