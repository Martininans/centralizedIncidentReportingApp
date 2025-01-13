package com.semicolon.africa.Centralized_Incident_Reporting_App.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Role role;

    @NotNull
    @Size(min = 6)
    private String password;

    @OneToMany(mappedBy = "reporter", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Incident> reportedIncidents;
    public enum Role {
        ADMIN, USER, LAW_ENFORCEMENT
    }
}
