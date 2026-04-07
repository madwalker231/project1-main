package com.skillstormpg1.project1.models;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "regulatory_requirements")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class RegulatoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "Requirement name is required.")
    private String name; 

    @Column(name = "governing_body", nullable = false)
    @NotBlank(message = "Governing body is required.")
    private String governingBody; // Example: SEC, FINRA, FCA, etc.

    @Column(name = "requirement_type", nullable = false)
    @NotBlank(message = "Requirement type is required.")
    private String requirementType; // Example: Reporting, Disclosure, etc.

    @Column(name = "effective_date", nullable = false)
    @NotNull(message = "Effective data is required.")
    @FutureOrPresent(message = "Effective dat cannot be in the past.")
    private LocalDate effectiveDate; 

    @Column(nullable = false)
    @NotBlank(message = "Status is required.")
    @Pattern(regexp = "^(Active|Pending|Superseded)$")
    private String status; // Example: Active, Suspended, etc.

    @OneToMany(mappedBy = "requirements", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<ComplianceObligation> obligations;
}
