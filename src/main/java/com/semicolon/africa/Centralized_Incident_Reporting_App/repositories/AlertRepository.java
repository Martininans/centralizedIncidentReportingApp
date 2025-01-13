package com.semicolon.africa.Centralized_Incident_Reporting_App.repositories;

import com.semicolon.africa.Centralized_Incident_Reporting_App.models.Alert;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlertRepository extends JpaRepository<Alert, Long> {
}
