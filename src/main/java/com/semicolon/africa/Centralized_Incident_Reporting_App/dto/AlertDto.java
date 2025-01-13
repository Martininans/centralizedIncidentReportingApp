package com.semicolon.africa.Centralized_Incident_Reporting_App.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AlertDto {
    private Long id;
    private String status;
    private String timestamp;
}
