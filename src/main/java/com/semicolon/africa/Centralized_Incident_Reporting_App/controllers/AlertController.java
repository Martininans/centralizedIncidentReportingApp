package com.semicolon.africa.Centralized_Incident_Reporting_App.controllers;

import com.semicolon.africa.Centralized_Incident_Reporting_App.dto.AlertDto;
import com.semicolon.africa.Centralized_Incident_Reporting_App.models.Alert;
import com.semicolon.africa.Centralized_Incident_Reporting_App.services.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alerts")
public class AlertController {
    private final AlertService alertService;

    public AlertController(AlertService alertService) {
        this.alertService = alertService;
    }

    @PostMapping
    public ResponseEntity<AlertDto> createAlert(@RequestBody AlertDto alertDto) {
        AlertDto createdAlert = alertService.createAlert(alertDto);
        return ResponseEntity.ok(createdAlert);
    }

    @GetMapping
    public ResponseEntity<List<AlertDto>> getAllAlerts() {
        List<AlertDto> alerts = alertService.getAllAlerts();
        if (alerts.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(alerts);
    }
}
