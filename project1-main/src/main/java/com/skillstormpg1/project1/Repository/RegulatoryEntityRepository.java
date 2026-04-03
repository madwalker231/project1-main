package com.skillstormpg1.project1.Repository;

import com.skillstormpg1.project1.models.RegulatoryEntity;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegulatoryEntityRepository extends JpaRepository<RegulatoryEntity, Long> {
    // Queries Database to filer Regulations by Name, Governing body (SEC, FDIC, Etc.), and Regulatory status (Active, Non-Active, Etc.)
    List<RegulatoryEntity> findByNameContainingIgnoreCase(String name);
    List<RegulatoryEntity> findByGoverningBody(String governingBody);
    List<RegulatoryEntity> findByStatus(String status);

    // Finds regulations effective between today and 60 days from now
    List<RegulatoryEntity> findByEffectiveDateBetween(LocalDate start, LocalDate end);
}
