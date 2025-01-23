package com.semicolon.africa.Centralized_Incident_Reporting_App.services;

import com.semicolon.africa.Centralized_Incident_Reporting_App.dto.UserDto;
import com.semicolon.africa.Centralized_Incident_Reporting_App.dto.UserRegistrationRespondsDto;
import com.semicolon.africa.Centralized_Incident_Reporting_App.exceptions.DuplicateUserException;
import com.semicolon.africa.Centralized_Incident_Reporting_App.exceptions.UserNotFoundException;
import com.semicolon.africa.Centralized_Incident_Reporting_App.models.User;
import com.semicolon.africa.Centralized_Incident_Reporting_App.repositories.UserRepository;
import com.semicolon.africa.Centralized_Incident_Reporting_App.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserRegistrationRespondsDto createUser(UserDto userDto) {
        if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
            throw new DuplicateUserException("A user with email " + userDto.getEmail() + " already exists");
        }
        User user = mapToEntity(userDto);
        user.setPassword(passwordEncoder.encode(userDto.getPassword())); // Encrypt password
        User savedUser = userRepository.save(user);
        return mapToRegistrationResponseDto(savedUser);
    }
    @Override
    public String loginUser(String email, String password) {
        // Fetch user by email
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid credentials");
        }

        // Generate JWT token
        return jwtUtil.generateToken(userDetails);
    }
    @Override
    public Optional<UserDto> getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + id + " not found"));
        return Optional.of(mapToDTO(user));
    }
    @Override
    public Optional<UserDto> getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User with email " + email + " not found"));
        return Optional.of(mapToDTO(user));
    }
    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
    private User mapToEntity(UserDto userDTO) {
        return User.builder()
                .name(userDTO.getName())
                .email(userDTO.getEmail())
                .phoneNumber(userDTO.getPhoneNumber())
                .role(User.Role.valueOf(userDTO.getRole()))
                .username(userDTO.getUserName())
                .build();
    }
    private UserDto mapToDTO(User user) {
        return UserDto.builder()
                .name(user.getName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .role(String.valueOf(user.getRole()))
                .build();
    }
    private UserRegistrationRespondsDto mapToRegistrationResponseDto(User user) {
        UserRegistrationRespondsDto response = new UserRegistrationRespondsDto();
        response.setId(String.valueOf(user.getId()));
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        response.setPhoneNumber(user.getPhoneNumber());
        response.setRole(String.valueOf(user.getRole()));
        response.setUserName(user.getUsername());
        return response;
    }
}