package com.semicolon.africa.Centralized_Incident_Reporting_App.services;

import com.semicolon.africa.Centralized_Incident_Reporting_App.dto.AlertDto;
import com.semicolon.africa.Centralized_Incident_Reporting_App.exceptions.AlertNotFoundException;
import com.semicolon.africa.Centralized_Incident_Reporting_App.models.Alert;
import com.semicolon.africa.Centralized_Incident_Reporting_App.repositories.AlertRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlertServiceImpl implements AlertService{
    private final AlertRepository alertRepository;

    public AlertServiceImpl(AlertRepository alertRepository) {
        this.alertRepository = alertRepository;
    }

    @Override
    public AlertDto createAlert(AlertDto alertDto) {
        // Map DTO to Entity
        Alert alert = mapToEntity(alertDto);

        // Save Entity
        Alert savedAlert = alertRepository.save(alert);

        // Map Entity to DTO
        return mapToDTO(savedAlert);
    }

    @Override
    public List<AlertDto> getAllAlerts() {
        List<Alert> alerts = alertRepository.findAll();
        if (alerts.isEmpty()) {
            throw new AlertNotFoundException("No alerts found.");
        }
        return alerts.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private Alert mapToEntity(AlertDto alertDto) {
        return Alert.builder()
                .status(alertDto.getStatus())
                .timestamp(LocalDateTime.parse(alertDto.getTimestamp()))
                .build();
    }

    private AlertDto mapToDTO(Alert alert) {
        return AlertDto.builder()
                .id(alert.getId())
                .status(alert.getStatus())
                .timestamp(String.valueOf(alert.getTimestamp()))
                .build();
    }
}
