package org.example.logitrack.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.example.logitrack.entity.Role;

@Getter
@Builder
@AllArgsConstructor
public class AuthResponse {
    private String token;
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private Role role;
}
