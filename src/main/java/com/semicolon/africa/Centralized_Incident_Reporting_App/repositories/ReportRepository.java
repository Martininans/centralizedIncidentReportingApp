package com.semicolon.africa.Centralized_Incident_Reporting_App.repositories;

import com.semicolon.africa.Centralized_Incident_Reporting_App.models.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Long> {
}
