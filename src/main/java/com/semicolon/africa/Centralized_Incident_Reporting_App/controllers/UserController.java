package com.semicolon.africa.Centralized_Incident_Reporting_App.controllers;

import com.semicolon.africa.Centralized_Incident_Reporting_App.dto.LoginRequestDto;
import com.semicolon.africa.Centralized_Incident_Reporting_App.dto.LoginResponseDto;
import com.semicolon.africa.Centralized_Incident_Reporting_App.dto.UserDto;
import com.semicolon.africa.Centralized_Incident_Reporting_App.dto.UserRegistrationRespondsDto;
import com.semicolon.africa.Centralized_Incident_Reporting_App.services.AuthService;
import com.semicolon.africa.Centralized_Incident_Reporting_App.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping()
public class UserController {
    private final UserService userService;
    private AuthService authService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Register a new user
    @PostMapping("/register-user")
    public ResponseEntity<UserRegistrationRespondsDto> registerUser(@RequestBody UserDto userDto) {
        UserRegistrationRespondsDto newUser = userService.createUser(userDto);
        return ResponseEntity.ok(newUser);
    }
//    @PostMapping("/login")
//    public ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginRequestDto loginRequestDto) {
//        System.out.println("Login request received: " + loginRequestDto);
//        return ResponseEntity.ok(authService.login(loginRequestDto));
//
//    }

    // Get user by ID
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get all users
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
}
