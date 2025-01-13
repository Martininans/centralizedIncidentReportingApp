package com.semicolon.africa.Centralized_Incident_Reporting_App.exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message) {
        super(message);
    }
}
