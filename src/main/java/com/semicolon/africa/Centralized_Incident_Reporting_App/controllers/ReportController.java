package com.semicolon.africa.Centralized_Incident_Reporting_App.controllers;

import com.semicolon.africa.Centralized_Incident_Reporting_App.dto.ReportDto;
import com.semicolon.africa.Centralized_Incident_Reporting_App.models.Report;
import com.semicolon.africa.Centralized_Incident_Reporting_App.services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportController {
    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping
    public ResponseEntity<ReportDto> createReport(@RequestBody ReportDto reportDto) {
        ReportDto createdReport = reportService.createReport(reportDto);
        return ResponseEntity.ok(createdReport);
    }

    // You can add more methods for fetching reports, e.g., by incident ID or user ID
    @GetMapping("/{id}")
    public ResponseEntity<ReportDto> getReportById(@PathVariable Long id) {
        try {
            ReportDto reportDto = reportService.getReportById(id);
            return ResponseEntity.ok(reportDto);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // You can also expose a method to get all reports
    @GetMapping
    public ResponseEntity<List<ReportDto>> getAllReports() {
        List<ReportDto> reports = reportService.getAllReports();
        if (reports.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(reports);
        }
    }
}
