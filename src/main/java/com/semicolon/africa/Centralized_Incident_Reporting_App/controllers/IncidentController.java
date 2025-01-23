package com.semicolon.africa.Centralized_Incident_Reporting_App.controllers;

import com.semicolon.africa.Centralized_Incident_Reporting_App.dto.IncidentDto;
import com.semicolon.africa.Centralized_Incident_Reporting_App.models.Incident;
import com.semicolon.africa.Centralized_Incident_Reporting_App.services.IncidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/incidents")
public class IncidentController {
   //this is a method in charge of in coming request
    private final IncidentService incidentService;

    public IncidentController(IncidentService incidentService) {
        this.incidentService = incidentService;
    }

    @PostMapping
    public ResponseEntity<IncidentDto> reportIncident(@RequestBody IncidentDto incidentDto) {
        IncidentDto reportedIncident = incidentService.reportIncident(incidentDto);
        return ResponseEntity.ok(reportedIncident);
    }

    @GetMapping("/{location}")
    public ResponseEntity<List<IncidentDto>> getIncidentsByLocation(@PathVariable String location) {
        List<IncidentDto> incidents = incidentService.getIncidentsByLocation(location);
        if (incidents.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(incidents);
    }

    @GetMapping
    public ResponseEntity<List<IncidentDto>> getAllIncidents() {
        List<IncidentDto> incidents = incidentService.getAllIncidents();
        if (incidents.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(incidents);
    }

}
