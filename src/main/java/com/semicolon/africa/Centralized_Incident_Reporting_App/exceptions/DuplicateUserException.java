package com.semicolon.africa.Centralized_Incident_Reporting_App.exceptions;

public class DuplicateUserException extends RuntimeException {
    public DuplicateUserException(String message) {
        super(message);
    }
}
