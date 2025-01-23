package com.semicolon.africa.Centralized_Incident_Reporting_App.services;

import com.semicolon.africa.Centralized_Incident_Reporting_App.dto.UserDto;
import com.semicolon.africa.Centralized_Incident_Reporting_App.dto.UserRegistrationRespondsDto;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserRegistrationRespondsDto createUser(UserDto userDto);
    public String loginUser(String email, String password);
    Optional<UserDto> getUserById(Long id);
    Optional<UserDto> getUserByEmail(String email);
    List<UserDto> getAllUsers();
}
