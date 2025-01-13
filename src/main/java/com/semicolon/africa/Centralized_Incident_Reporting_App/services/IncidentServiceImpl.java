package com.semicolon.africa.Centralized_Incident_Reporting_App.services;

import com.semicolon.africa.Centralized_Incident_Reporting_App.dto.IncidentDto;
import com.semicolon.africa.Centralized_Incident_Reporting_App.exceptions.IncidentNotFoundException;
import com.semicolon.africa.Centralized_Incident_Reporting_App.models.Incident;
import com.semicolon.africa.Centralized_Incident_Reporting_App.repositories.IncidentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IncidentServiceImpl implements IncidentService{
    private final IncidentRepository incidentRepository;

    public IncidentServiceImpl(IncidentRepository incidentRepository) {
        this.incidentRepository = incidentRepository;
    }

    @Override
    public IncidentDto reportIncident(IncidentDto incidentDto) {
        // Map DTO to Entity
        Incident incident = mapToEntity(incidentDto);

        // Save Entity
        Incident savedIncident = incidentRepository.save(incident);

        // Map Entity to DTO
        return mapToDTO(savedIncident);
    }

    @Override
    public List<IncidentDto> getAllIncidents() {
        List<Incident> incidents = incidentRepository.findAll();
        if (incidents.isEmpty()) {
            throw new IncidentNotFoundException("No incidents found.");
        }
        return incidents.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<IncidentDto> getIncidentsByLocation(String location) {
        List<Incident> incidents = incidentRepository.findByLocation(location);
        if (incidents.isEmpty()) {
            throw new IncidentNotFoundException("No incidents found at location: " + location);
        }
        return incidents.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private Incident mapToEntity(IncidentDto incidentDto) {
        return Incident.builder()
                .type(incidentDto.getType())
                .location(incidentDto.getLocation())
                .description(incidentDto.getDescription())
                .timestamp(incidentDto.getTimestamp())
                .media(incidentDto.getMedia())
                .build();
    }

    private IncidentDto mapToDTO(Incident incident) {
        return IncidentDto.builder()
                .userId(incident.getId())
                .type(incident.getType())
                .location(incident.getLocation())
                .description(incident.getDescription())
                .timestamp(incident.getTimestamp())
                .media(incident.getMedia())
                .build();
    }

}
