package com.semicolon.africa.Centralized_Incident_Reporting_App.services;

import com.semicolon.africa.Centralized_Incident_Reporting_App.dto.AlertDto;
import com.semicolon.africa.Centralized_Incident_Reporting_App.models.Alert;

import java.util.List;

public interface AlertService {
    AlertDto createAlert(AlertDto alertDto);
    List<AlertDto> getAllAlerts();
}
