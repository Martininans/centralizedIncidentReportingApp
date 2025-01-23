package com.semicolon.africa.Centralized_Incident_Reporting_App.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReportResponseDto {
    private Long reportId;
    private Long incidentId;
    private String incidentDescription; // Optional, if included in response
    private Long createdByUserId;
    private String createdByUserName; // Optional, include username if needed
    private String createdOn;
    private String actionTaken;

}
