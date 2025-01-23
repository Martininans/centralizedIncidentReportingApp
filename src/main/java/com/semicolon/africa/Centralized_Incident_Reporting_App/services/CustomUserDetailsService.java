package com.semicolon.africa.Centralized_Incident_Reporting_App.services;

import com.semicolon.africa.Centralized_Incident_Reporting_App.models.User;
import com.semicolon.africa.Centralized_Incident_Reporting_App.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // This method is from UserDetailsService
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Assuming username is the email in this case
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));

        // Create and return the UserDetails object using the User entity data
        return org.springframework.security.core.userdetails.User
                .builder()
                .username(user.getEmail())  // or use user.getUsername() if your model has it
                .password(user.getPassword())
                .roles(user.getRole().name())  // Make sure you have roles in your model
                .build();
    }

}
