package com.semicolon.africa.Centralized_Incident_Reporting_App.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReportDto {
    private Long id;
    private Long incidentId;
    private String actionTaken;
    private String createdBy;
    private String createdOn;
}
