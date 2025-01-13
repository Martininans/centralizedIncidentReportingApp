package com.semicolon.africa.Centralized_Incident_Reporting_App.services;

import com.semicolon.africa.Centralized_Incident_Reporting_App.dto.ReportDto;
import com.semicolon.africa.Centralized_Incident_Reporting_App.models.Report;

import java.util.List;

public interface ReportService {
    ReportDto createReport(ReportDto reportDto);
    ReportDto getReportById(Long id);

    List<ReportDto> getAllReports();

}
