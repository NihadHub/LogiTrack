package org.example.logitrack.dto;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AddProductRequest {
    private Long produitId;
    private Integer quantite;
}