package com.skillstormpg1.project1.models;

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
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "business_entities")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class BusinessEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Size(max = 100)
    private String name; 

    @Column(nullable = false)
    @NotBlank(message = "Entity type is required.")
    @Pattern(regexp = "^(Bank|Broker-Dealer|Investment Firm|Insurance Company)$",
        message = "Invalid entity type."
    )
    private String entityType; // Example: Bank, Broker-Dealer, etc.

    @Column(nullable = false)
    @NotBlank(message = "Jurisdiction is required")
    private String jurisdiction; // Example: US, EU 

    @Column(nullable = false)
    @NotBlank(message = "Status is required")
    @Pattern(regexp = "^(Active|Inactive|Pending)$")
    private String status; // Example: Active, Under Review, etc.

    // Allows removal of BusinessEntity to cascade delete associated obligations
    // Foreign key error - DataIntegrityViolationException fix
    @OneToMany(mappedBy = "entity", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference // Prevents infinite recursion
    private List<ComplianceObligation> obligations;
}
