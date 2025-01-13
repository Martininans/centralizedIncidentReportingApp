package com.semicolon.africa.Centralized_Incident_Reporting_App.exceptions;

public class AlertNotFoundException extends RuntimeException {
    public AlertNotFoundException(String message) {
        super(message);
    }
}
