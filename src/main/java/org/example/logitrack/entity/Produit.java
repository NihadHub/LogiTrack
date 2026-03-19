package org.example.logitrack.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "produits")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    private String categorie;

    @Column(nullable = false)
    private Double prix;

    @Column(nullable = false)
    private Integer quantiteStock;

    @OneToMany(mappedBy = "produit", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<LigneCommande> lignesCommande = new ArrayList<>();
}