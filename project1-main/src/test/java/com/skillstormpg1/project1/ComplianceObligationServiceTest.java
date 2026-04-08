package com.skillstormpg1.project1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.skillstormpg1.project1.Repository.ComplianceObligationRepository;
import com.skillstormpg1.project1.models.BusinessEntity;
import com.skillstormpg1.project1.models.ComplianceObligation;
import com.skillstormpg1.project1.models.RegulatoryEntity;
import com.skillstormpg1.project1.services.ComplianceObligationService;

@ExtendWith(MockitoExtension.class)
public class ComplianceObligationServiceTest {
    @Mock
    private ComplianceObligationRepository obligationRepo;

    @InjectMocks
    private ComplianceObligationService obligationService;

    @Test
    public void testServiceObligation_NewRecord() { // Test new record creation
        // Create dummy data
        BusinessEntity entity = new BusinessEntity();
        entity.setId(1L);
        RegulatoryEntity req = new RegulatoryEntity();
        req.setId(1L);
        ComplianceObligation obligation = new ComplianceObligation();
        obligation.setEntity(entity);
        obligation.setRequirement(req);
        obligation.setStatus("In Progress");

        // Have Mockito search, return empty, mocking a new record
        when(obligationRepo.findByEntityAndRequirement(entity, req)).thenReturn(Optional.empty());
        when(obligationRepo.save(any())).thenReturn(obligation);

        ComplianceObligation result = obligationService.savedComplianceObligation(obligation);

        // Verify test worked
        assertNotNull(result);
        assertEquals("In Progress", result.getStatus());
        verify(obligationRepo, times(1)).save(any());
    }

    @Test
    public void testSaveObligation_UpdateStatus() {
        BusinessEntity entity = new BusinessEntity();
        RegulatoryEntity req = new RegulatoryEntity();
        ComplianceObligation existing = new ComplianceObligation();
        existing.setStatus("Non-Compliant");

        ComplianceObligation updateRequest = new ComplianceObligation();
        updateRequest.setEntity(entity);
        updateRequest.setRequirement(req);
        updateRequest.setStatus("Compliant");

        when(obligationRepo.findByEntityAndRequirement(entity, req)).thenReturn(Optional.of(existing));
        when(obligationRepo.save(any())).thenReturn(existing);

        ComplianceObligation result = obligationService.savedComplianceObligation(updateRequest);

        assertEquals("Compliant", result.getStatus());
        verify(obligationRepo, times(1)).save(existing);
    }
}
