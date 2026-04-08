package com.skillstormpg1.project1.models;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Builder
public class ComplianceAlertDTO {
    private Long obligationId;
    private String entityName;
    private String requirementName;

    public ComplianceAlertDTO(Long id, String entityName, String requirementName) {
        this.obligationId = id;
        this.entityName = entityName;
        this.requirementName = requirementName;
    }

}
