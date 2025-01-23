package com.semicolon.africa.Centralized_Incident_Reporting_App.services;

import com.semicolon.africa.Centralized_Incident_Reporting_App.dto.IncidentDto;
import com.semicolon.africa.Centralized_Incident_Reporting_App.exceptions.IncidentNotFoundException;
import com.semicolon.africa.Centralized_Incident_Reporting_App.exceptions.UserNotFoundException;
import com.semicolon.africa.Centralized_Incident_Reporting_App.models.Incident;
import com.semicolon.africa.Centralized_Incident_Reporting_App.models.User;
import com.semicolon.africa.Centralized_Incident_Reporting_App.repositories.IncidentRepository;
import com.semicolon.africa.Centralized_Incident_Reporting_App.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IncidentServiceImpl implements IncidentService{
    private final IncidentRepository incidentRepository;
    private final UserRepository userRepository;

    public IncidentServiceImpl(IncidentRepository incidentRepository, UserRepository userRepository) {
        this.incidentRepository = incidentRepository;
        this.userRepository = userRepository;
    }

    @Override
    public IncidentDto reportIncident(IncidentDto incidentDto) {
        // Fetch the User entity using the userId
        User reporter = userRepository.findById(incidentDto.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + incidentDto.getUserId()));

        // Map DTO to Entity and set reporter
        Incident incident = mapToEntity(incidentDto);
        incident.setReporter(reporter);

        // Save Incident
        Incident savedIncident = incidentRepository.save(incident);

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
                .status("Pending") // Example: Default status
                .build();
    }

    private IncidentDto mapToDTO(Incident incident) {
        return IncidentDto.builder()
                .userId(incident.getReporter().getId()) // Map reporter's ID
                .type(incident.getType())
                .location(incident.getLocation())
                .description(incident.getDescription())
                .timestamp(incident.getTimestamp())
                .media(incident.getMedia())
                .build();
    }
}
