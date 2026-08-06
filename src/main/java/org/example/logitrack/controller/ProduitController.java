package org.example.logitrack.controller;
import  org.example.logitrack.entity.Produit;
import  org.example.logitrack.service.ProduitService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProduitController {
    private final ProduitService produitService;

@PostMapping
@PreAuthorize("hasAnyRole('ADMIN','MANAGER')")

    public ResponseEntity<Produit> addProduit(@RequestBody Produit produit){
    Produit saved = produitService.addProduit(produit);
    return new ResponseEntity<>(saved,HttpStatus.CREATED);
}

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','AGENT')")
    public ResponseEntity<Page<Produit>> getAllProduits(
            @PageableDefault(size = 10, sort = "nom") Pageable pageable) {
        return ResponseEntity.ok(produitService.getAllProduits(pageable));
    }

@GetMapping("/{id}")
@PreAuthorize("hasAnyRole('ADMIN','MANAGER','AGENT')")

    public ResponseEntity <Produit> getProduitById(@PathVariable Long id){
    return ResponseEntity.ok(produitService.getProduitById(id));
}
 @DeleteMapping("/{id}")
 @PreAuthorize("hasRole('ADMIN')")

 public ResponseEntity<String> deleteProduit(@PathVariable Long id){
    produitService.deleteProduit(id);
    return ResponseEntity.ok("Produit supprimé avec succés");
}

@GetMapping("/low-stock")
@PreAuthorize("hasAnyRole('ADMIN','MANAGER')")

    public ResponseEntity<List<Produit>> getLowStockProducts(){
    return ResponseEntity.ok(produitService.getLowStockProducts()) ;
}

@GetMapping("/price/{price}")
@PreAuthorize("hasAnyRole('ADMIN','MANAGER','AGENT')")

    public ResponseEntity<List<Produit>> getByPrixInferieur(@PathVariable Double price){
    return ResponseEntity.ok(produitService.getProduitsByPrixInferieur(price));
}

@GetMapping("/category/{category}")
@PreAuthorize("hasAnyRole('ADMIN','MANAGER','AGENT')")

public ResponseEntity<List<Produit>> getByCategorie(@PathVariable String category) {
    return ResponseEntity.ok(produitService.getProduitsByCategorie(category));
}




}
