package com.BiologicalMaterialsSystem.BiologicalMaterialsSystem.model;

import com.BiologicalMaterialsSystem.BiologicalMaterialsSystem.enums.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userID;

    @JsonProperty("first_name")
    @NotBlank(message = "First name is required")
    @Column(nullable = false)
    private String firstName;

    @JsonProperty("last_name")
    @NotBlank(message = "Last name is required")
    @Column(nullable = false)
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @JsonProperty("access_rights")
    @NotBlank(message = "Access rights are required")
    @Column(nullable = false)
    private String accessRights;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "Login is required")
    @Email(message = "Login must be a valid email address")
    private String login;

    @Column(nullable = false)
    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String password;
}
