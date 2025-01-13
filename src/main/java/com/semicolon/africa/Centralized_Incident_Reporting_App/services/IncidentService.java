package com.semicolon.africa.Centralized_Incident_Reporting_App.services;

import com.semicolon.africa.Centralized_Incident_Reporting_App.dto.IncidentDto;
import com.semicolon.africa.Centralized_Incident_Reporting_App.models.Incident;

import java.util.List;

public interface IncidentService {
    IncidentDto reportIncident(IncidentDto incidentDto);
    List<IncidentDto> getAllIncidents();
    List<IncidentDto> getIncidentsByLocation(String location);
}
