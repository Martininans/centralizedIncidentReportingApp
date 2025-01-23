package com.semicolon.africa.Centralized_Incident_Reporting_App.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegistrationRespondsDto {
    private String id;
    private String name;
    private String email;
    private String phoneNumber;
    private String role;
    private String userName;
}
