package com.skillstormpg1.project1.models;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DashboardDTO {
    private long totalEntities;
    private long totalRequirements;
    private long nonCompliantCount;
    private Map<String, Long> entitiesByType; 
    private double overallComplianceScore;
    private List<RegulatoryEntity> upcomingRequirements;
    private List<ComplianceObligation> criticalObligations;
}
