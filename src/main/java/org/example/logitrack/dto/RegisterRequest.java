package org.example.logitrack.dto;

import lombok.Getter;
import lombok.Setter;
import org.example.logitrack.entity.Role;

@Getter @Setter
public class RegisterRequest {
    private String nom;
    private String prenom;
    private String email;
    private String password;
    private Role role;
}