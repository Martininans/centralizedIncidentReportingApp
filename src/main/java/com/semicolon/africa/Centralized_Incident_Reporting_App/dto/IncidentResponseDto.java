package com.semicolon.africa.Centralized_Incident_Reporting_App.dto;

import lombok.Data;

@Data
public class IncidentResponseDto {
    private Long id;
    private String type;
    private String location;
    private String description;
    private String status;
}
