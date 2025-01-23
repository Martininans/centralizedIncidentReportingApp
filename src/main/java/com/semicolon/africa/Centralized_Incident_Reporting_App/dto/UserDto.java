package com.semicolon.africa.Centralized_Incident_Reporting_App.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor // Generates a no-argument constructor

public class UserDto {
    private String name;
    private String email;
    private String phoneNumber;
    private String password;
    private String role;
    private String userName;
}
