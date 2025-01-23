package com.semicolon.africa.Centralized_Incident_Reporting_App.dto;

import com.semicolon.africa.Centralized_Incident_Reporting_App.models.User;
import lombok.Builder;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Data
@Builder
public class ReportDto {
    @NotNull(message = "Incident ID is required")
    private Long incidentId;

    @NotBlank(message = "Action taken is required")
    private String actionTaken;

    @NotNull(message = "Created by (User ID) is required")
    private Long userId;

    @NotNull(message = "Created on is required")
    private String createdOn;
}

