package com.semicolon.africa.Centralized_Incident_Reporting_App.repositories;

import com.semicolon.africa.Centralized_Incident_Reporting_App.models.Incident;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IncidentRepository extends JpaRepository<Incident, Long> {
    List<Incident> findByLocation(String location);
}
