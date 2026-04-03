package com.skillstormpg1.project1.config;

import com.skillstormpg1.project1.models.BusinessEntity;
import com.skillstormpg1.project1.models.RegulatoryEntity;
import com.skillstormpg1.project1.models.ComplianceObligation;
import com.skillstormpg1.project1.Repository.BusinessEntityRepository;
import com.skillstormpg1.project1.Repository.RegulatoryEntityRepository;
import com.skillstormpg1.project1.Repository.ComplianceObligationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class SeedDataConfig {
    @Bean
    CommandLineRunner intiDatabase(
        BusinessEntityRepository entityRepo,
        RegulatoryEntityRepository reqRepo,
        ComplianceObligationRepository obligationRepo) {
            return args -> {
                // Create a Simple Entity
                BusinessEntity bank = BusinessEntity.builder()
                    .name("Global Atlantic Bank")
                    .entityType("Bank")
                    .jurisdiction("US")
                    .status("Active")
                    .build();

                // Create a sample requirement
                RegulatoryEntity secRule = RegulatoryEntity.builder()
                    .name("Section 13F Reporting")
                    .governingBody("SEC")
                    .requirementType("Reporting")
                    .effectiveDate(LocalDate.of(2026, 6, 1))
                    .status("Active")
                    .build();

                // Save and generate IDs
                entityRepo.save(bank);
                reqRepo.save(secRule);

                // Link to Obligation
                ComplianceObligation obligation = ComplianceObligation.builder()
                    .entity(bank)
                    .requirements(secRule)
                    .status("In Progress")
                    .lastReviewDate(LocalDate.now())
                    .notes("Initial filing setup started.")
                    .build();

                obligationRepo.save(obligation);

                System.out.println("--- Database Seeded Successfully ---");
            };
        }
}
