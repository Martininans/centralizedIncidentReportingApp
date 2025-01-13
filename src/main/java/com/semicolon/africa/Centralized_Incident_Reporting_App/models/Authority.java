package com.semicolon.africa.Centralized_Incident_Reporting_App.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String contact;

    private String areaOfResponsibility;

    @OneToMany(mappedBy = "assignedAuthority", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Incident> managedIncidents;
}
