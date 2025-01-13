package com.semicolon.africa.Centralized_Incident_Reporting_App.services;

import com.semicolon.africa.Centralized_Incident_Reporting_App.dto.ReportDto;
import com.semicolon.africa.Centralized_Incident_Reporting_App.exceptions.IncidentNotFoundException;
import com.semicolon.africa.Centralized_Incident_Reporting_App.exceptions.ReportNotFoundException;
import com.semicolon.africa.Centralized_Incident_Reporting_App.exceptions.UserNotFoundException;
import com.semicolon.africa.Centralized_Incident_Reporting_App.models.Incident;
import com.semicolon.africa.Centralized_Incident_Reporting_App.models.Report;
import com.semicolon.africa.Centralized_Incident_Reporting_App.models.User;
import com.semicolon.africa.Centralized_Incident_Reporting_App.repositories.IncidentRepository;
import com.semicolon.africa.Centralized_Incident_Reporting_App.repositories.ReportRepository;
import com.semicolon.africa.Centralized_Incident_Reporting_App.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportServiceImpl implements ReportService{
    private final ReportRepository reportRepository;
    private final IncidentRepository incidentRepository;
    private final UserRepository userRepository;

    public ReportServiceImpl(ReportRepository reportRepository, IncidentRepository incidentRepository, UserRepository userRepository) {
        this.reportRepository = reportRepository;
        this.incidentRepository = incidentRepository;
        this.userRepository = userRepository; // Initialize User repository
    }

    @Override
    public ReportDto createReport(ReportDto reportDto) {
        Incident incident = incidentRepository.findById(reportDto.getIncidentId())
                .orElseThrow(() -> new IncidentNotFoundException("Incident not found with ID: " + reportDto.getIncidentId()));

        User user = userRepository.findById(Long.valueOf(reportDto.getCreatedBy()))
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + reportDto.getCreatedBy()));

        LocalDateTime createdOn = LocalDateTime.parse(reportDto.getCreatedOn());
        Report report = new Report();
        report.setIncident(incident);
        report.setCreatedBy(user);
        report.setCreatedOn(createdOn);
        report.setActionTaken(reportDto.getActionTaken());

        Report savedReport = reportRepository.save(report);
        return mapToDto(savedReport);
    }

    @Override
    public ReportDto getReportById(Long id) {
        Report report = reportRepository.findById(id)
                .orElseThrow(() -> new ReportNotFoundException("Report not found with ID: " + id));
        return mapToDto(report);
    }

    @Override
    public List<ReportDto> getAllReports() {
        List<Report> reports = reportRepository.findAll();
        if (reports.isEmpty()) {
            throw new ReportNotFoundException("No reports found.");
        }
        return reports.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private ReportDto mapToDto(Report report) {
        return ReportDto.builder()
                .id(report.getId())
                .incidentId(report.getIncident().getId())
                .createdBy(String.valueOf(report.getCreatedBy().getId()))
                .createdOn(report.getCreatedOn().toString())
                .actionTaken(report.getActionTaken())
                .build();
    }
}
