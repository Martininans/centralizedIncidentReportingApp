package com.semicolon.africa.Centralized_Incident_Reporting_App.services;

import com.semicolon.africa.Centralized_Incident_Reporting_App.dto.ReportDto;
import com.semicolon.africa.Centralized_Incident_Reporting_App.dto.ReportResponseDto;
import com.semicolon.africa.Centralized_Incident_Reporting_App.models.Report;

import java.util.List;

public interface ReportService {
    ReportResponseDto createReport(ReportDto reportDto);
    ReportResponseDto getReportById(Long id); // Updated to use `ReportResponseDto`
    List<ReportResponseDto> getAllReports();
}
