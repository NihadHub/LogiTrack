package org.example.logitrack.controller;

import org.example.logitrack.entity.Produit;
import org.example.logitrack.service.ProduitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/statistics")
@RequiredArgsConstructor
public class StatisticsController {
    private final ProduitService produitService;

    @GetMapping("/top-product")
    public ResponseEntity<Produit> getTopProduct() {
        return ResponseEntity.ok(produitService.getMostOrderedProduct());
    }
}
