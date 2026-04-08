package com.skillstormpg1.project1.models;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "compliance_obligations")
@Getter 
@Setter 
@NoArgsConstructor 
@AllArgsConstructor 
@Builder

public class ComplianceObligation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "entity_id", nullable = false)
    @JsonBackReference
    private BusinessEntity entity;

    @ManyToOne
    @JoinColumn(name = "requirement_id", nullable = false)
    @JsonBackReference
    private RegulatoryEntity requirement;

    @Column(name = "obligation_status", nullable = false)
    private String status; // Example: Compliant, Non-Compliant, In Progress

    @Column(name = "last_review_date")
    private LocalDate lastReviewDate;

    @Column(columnDefinition = "TEXT")
    private String notes;

}
