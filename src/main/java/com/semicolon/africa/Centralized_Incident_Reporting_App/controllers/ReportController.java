package com.semicolon.africa.Centralized_Incident_Reporting_App.controllers;

import com.semicolon.africa.Centralized_Incident_Reporting_App.dto.ReportDto;
import com.semicolon.africa.Centralized_Incident_Reporting_App.dto.ReportResponseDto;
import com.semicolon.africa.Centralized_Incident_Reporting_App.models.Report;
import com.semicolon.africa.Centralized_Incident_Reporting_App.services.ReportService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportController {
    private final ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping
    public ResponseEntity<ReportResponseDto> createReport(@RequestBody @Valid ReportDto reportDto) {
        ReportResponseDto createdReport = reportService.createReport(reportDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdReport);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReportResponseDto> getReportById(@PathVariable Long id) {
        // Adjusted to use ReportResponseDto
        ReportResponseDto reportResponseDto = reportService.getReportById(id);
        return ResponseEntity.ok(reportResponseDto);
    }

    @GetMapping
    public ResponseEntity<List<ReportResponseDto>> getAllReports() {
        List<ReportResponseDto> reports = reportService.getAllReports();
        return ResponseEntity.ok(reports);
    }
}
