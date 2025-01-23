package com.semicolon.africa.Centralized_Incident_Reporting_App.services;

import com.semicolon.africa.Centralized_Incident_Reporting_App.dto.ReportDto;
import com.semicolon.africa.Centralized_Incident_Reporting_App.dto.ReportResponseDto;
import com.semicolon.africa.Centralized_Incident_Reporting_App.exceptions.IncidentNotFoundException;
import com.semicolon.africa.Centralized_Incident_Reporting_App.exceptions.ReportNotFoundException;
import com.semicolon.africa.Centralized_Incident_Reporting_App.exceptions.UserNotFoundException;
import com.semicolon.africa.Centralized_Incident_Reporting_App.models.Incident;
import com.semicolon.africa.Centralized_Incident_Reporting_App.models.Report;
import com.semicolon.africa.Centralized_Incident_Reporting_App.models.User;
import com.semicolon.africa.Centralized_Incident_Reporting_App.repositories.IncidentRepository;
import com.semicolon.africa.Centralized_Incident_Reporting_App.repositories.ReportRepository;
import com.semicolon.africa.Centralized_Incident_Reporting_App.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportServiceImpl implements ReportService {
    private final ReportRepository reportRepository;
    private final IncidentRepository incidentRepository;
    private final UserRepository userRepository;

    @Autowired
    public ReportServiceImpl(ReportRepository reportRepository, IncidentRepository incidentRepository, UserRepository userRepository) {
        this.reportRepository = reportRepository;
        this.incidentRepository = incidentRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ReportResponseDto createReport(ReportDto reportDto) {
        Incident incident = incidentRepository.findById(reportDto.getIncidentId())
                .orElseThrow(() -> new IncidentNotFoundException("Incident not found with ID: " + reportDto.getIncidentId()));

        User user = userRepository.findById(reportDto.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + reportDto.getUserId()));

        LocalDateTime createdOn;
        try {
            createdOn = LocalDateTime.parse(reportDto.getCreatedOn());
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid date format for 'createdOn'. Expected ISO 8601 format.", e);
        }

        Report report = Report.builder()
                .incident(incident)
                .createdBy(user)
                .createdOn(createdOn)
                .actionTaken(reportDto.getActionTaken())
                .build();

        Report savedReport = reportRepository.save(report);

        return mapToResponseDto(savedReport);
    }


    @Override
    public ReportResponseDto getReportById(Long id) {
        Report report = reportRepository.findById(id)
                .orElseThrow(() -> new ReportNotFoundException("Report not found with ID: " + id));
        return mapToResponseDto(report);
    }

    @Override
    public List<ReportResponseDto> getAllReports() {
        return reportRepository.findAll()
                .stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    private ReportResponseDto mapToResponseDto(Report report) {
        return ReportResponseDto.builder()
                .reportId(report.getId())
                .incidentId(report.getIncident().getId())
                .incidentDescription(report.getIncident().getDescription()) // Include if needed
                .createdByUserId(report.getCreatedBy().getId())
                .createdByUserName(report.getCreatedBy().getName()) // Include if needed
                .createdOn(report.getCreatedOn().toString())
                .actionTaken(report.getActionTaken())
                .build();
    }

}
