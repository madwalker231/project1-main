package com.skillstormpg1.project1.Repository;

import com.skillstormpg1.project1.models.BusinessEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessEntityRepository extends JpaRepository<BusinessEntity, Long> {
    // Counts the number of Business entities
    long count();
}
