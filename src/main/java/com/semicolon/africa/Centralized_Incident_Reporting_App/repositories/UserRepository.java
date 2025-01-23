package com.semicolon.africa.Centralized_Incident_Reporting_App.repositories;

import com.semicolon.africa.Centralized_Incident_Reporting_App.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
}
