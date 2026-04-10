package com.skillstormpg1.project1.models;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "regulatory_requirements")


public class RegulatoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "Requirement name is required.")
    @JsonProperty("name")
    private String name; 

    @Column(name = "governing_body", nullable = false)
    @NotBlank(message = "Governing body is required.")
    @JsonProperty("governingBody")
    private String governingBody; // Example: SEC, FINRA, FCA, etc.

    @Column(name = "requirement_type", nullable = false)
    @NotBlank(message = "Requirement type is required.")
    @JsonProperty("requirementType")
    private String requirementType; // Example: Reporting, Disclosure, etc.

    @Column(name = "effective_date", nullable = false)
    @NotNull(message = "Effective data is required.")
    @FutureOrPresent(message = "Effective dat cannot be in the past.")
    @JsonProperty("effectiveDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate effectiveDate; 

    @Column(nullable = false)
    @NotBlank(message = "Status is required.")
    @Pattern(regexp = "^(Active|Pending|Superseded)$")
    @JsonProperty("status")
    private String status; // Example: Active, Suspended, etc.

    @OneToMany(mappedBy = "requirement", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "requirement-obligations")
    private List<ComplianceObligation> obligations;

    public RegulatoryEntity() {}

    public RegulatoryEntity(Long id, @NotBlank(message = "Requirement name is required.") String name,
            @NotBlank(message = "Governing body is required.") String governingBody,
            @NotBlank(message = "Requirement type is required.") String requirementType,
            @NotNull(message = "Effective data is required.") @FutureOrPresent(message = "Effective dat cannot be in the past.") LocalDate effectiveDate,
            @NotBlank(message = "Status is required.") @Pattern(regexp = "^(Active|Pending|Superseded)$") String status,
            List<ComplianceObligation> obligations) {
        this.id = id;
        this.name = name;
        this.governingBody = governingBody;
        this.requirementType = requirementType;
        this.effectiveDate = effectiveDate;
        this.status = status;
        this.obligations = obligations;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGoverningBody() {
        return governingBody;
    }

    public void setGoverningBody(String governingBody) {
        this.governingBody = governingBody;
    }

    public String getRequirementType() {
        return requirementType;
    }

    public void setRequirementType(String requirementType) {
        this.requirementType = requirementType;
    }

    public LocalDate getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(LocalDate effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ComplianceObligation> getObligations() {
        return obligations;
    }

    public void setObligations(List<ComplianceObligation> obligations) {
        this.obligations = obligations;
    }

    
}
