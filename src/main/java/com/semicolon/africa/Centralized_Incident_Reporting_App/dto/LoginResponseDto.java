package com.semicolon.africa.Centralized_Incident_Reporting_App.dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDto {
    private String email;
    private String token;
    private String message;
}
