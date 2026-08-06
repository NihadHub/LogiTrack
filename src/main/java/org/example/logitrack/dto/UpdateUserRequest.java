package org.example.logitrack.dto;

import lombok.Getter;
import lombok.Setter;
import org.example.logitrack.entity.Role;

@Getter @Setter
public class UpdateUserRequest {
    private String nom;
    private String prenom;
    private String email;
    private Role role;
}