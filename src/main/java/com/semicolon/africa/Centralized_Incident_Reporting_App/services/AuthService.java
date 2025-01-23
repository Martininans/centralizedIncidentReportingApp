package com.semicolon.africa.Centralized_Incident_Reporting_App.services;

import com.semicolon.africa.Centralized_Incident_Reporting_App.dto.LoginRequestDto;
import com.semicolon.africa.Centralized_Incident_Reporting_App.dto.LoginResponseDto;
import com.semicolon.africa.Centralized_Incident_Reporting_App.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @Autowired
    public AuthService(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }
    public boolean validateToken(String token, UserDetails userDetails) {
        // Check if the token is valid by comparing it with user details
        return jwtUtil.isTokenValid(token, userDetails);
    }

    /**
     * Authenticates the user and returns a LoginResponseDto with the JWT token.
     *
     * @param loginRequestDto the login request containing email and password.
     * @return the login response containing token and user details.
     */
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        try {
            // Validate email and password
            if (loginRequestDto.getEmail() == null || loginRequestDto.getPassword() == null) {
                throw new IllegalArgumentException("Email and password must not be null");
            }

            // Authenticate user credentials
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequestDto.getEmail(),
                            loginRequestDto.getPassword()
                    )
            );

            // Load authenticated user details
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            // Generate JWT token
            String token = jwtUtil.generateToken(userDetails);

            // Return token and other user details
            return new LoginResponseDto(
                    userDetails.getUsername(),  // email or username
                    token,
                    "Login successful"
            );
        } catch (BadCredentialsException e) {
            // Handle authentication failure
            throw new RuntimeException("Invalid email or password", e);
        } catch (IllegalArgumentException e) {
            // Handle invalid argument error
            throw new RuntimeException(e.getMessage(), e);
        } catch (Exception e) {
            // Catch all other exceptions
            throw new RuntimeException("An error occurred during authentication", e);
        }
    }
}
