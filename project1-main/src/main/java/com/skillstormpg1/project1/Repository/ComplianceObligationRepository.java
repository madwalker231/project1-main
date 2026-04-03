package com.skillstormpg1.project1.Repository;

import com.skillstormpg1.project1.models.BusinessEntity;
import com.skillstormpg1.project1.models.ComplianceObligation;
import com.skillstormpg1.project1.models.RegulatoryEntity;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface ComplianceObligationRepository extends JpaRepository<ComplianceObligation, Long> {
    // Queries the database for business entities and regulations entities to connect regulations to business
    Optional<ComplianceObligation> findByEntityAndRequirements(BusinessEntity entity, RegulatoryEntity requirement);

    // Counts non compliant and compliant entities for dashboards
    long countByStatus(String status);
    long countByStatusIgnoreCase(String status);

    // Finds obligations based on status (Non-compliant, compliant, etc.)
    List<ComplianceObligation> findByStatusIgnoreCase(String status);
}
