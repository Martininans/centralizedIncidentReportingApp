package com.semicolon.africa.Centralized_Incident_Reporting_App.exceptions;

public class ReportNotFoundException extends RuntimeException {
    public ReportNotFoundException(String message) {
        super(message);
    }
}
