package com.semicolon.africa.Centralized_Incident_Reporting_App.controllers;

import com.semicolon.africa.Centralized_Incident_Reporting_App.dto.LoginRequestDto;
import com.semicolon.africa.Centralized_Incident_Reporting_App.dto.LoginResponseDto;
import com.semicolon.africa.Centralized_Incident_Reporting_App.security.JwtUtil;
import com.semicolon.africa.Centralized_Incident_Reporting_App.services.AuthService;
import com.semicolon.africa.Centralized_Incident_Reporting_App.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    @Autowired
    private JwtUtil jwtUtil;
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        // Delegate login logic to AuthService
        LoginResponseDto loginResponse = authService.login(loginRequestDto);
        return ResponseEntity.ok(loginResponse);
    }

    @GetMapping("/validate-token")
    public boolean validateToken(@RequestParam String token) {
        // Extract username from token
        String username = jwtUtil.extractUsername(token);

        // Load user details
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

        // Validate the token using AuthService
        return authService.validateToken(token, userDetails);
    }
}
