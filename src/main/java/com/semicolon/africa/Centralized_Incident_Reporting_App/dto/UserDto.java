package com.semicolon.africa.Centralized_Incident_Reporting_App.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private String password;
    private String role;
}
