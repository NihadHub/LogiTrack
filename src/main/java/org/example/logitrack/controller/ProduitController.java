package org.example.logitrack.controller;
import  org.example.logitrack.entity.Produit;
import  org.example.logitrack.service.ProduitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProduitController {
    private final ProduitService produitService;

@PostMapping
    public ResponseEntity<Produit> addProduit(@RequestBody Produit produit){
    Produit saved = produitService.addProduit(produit);
    return new ResponseEntity<>(saved,HttpStatus.CREATED);
}

    @GetMapping
    public ResponseEntity<List<Produit>> getAllProduits() {
        return ResponseEntity.ok(produitService.getAllProduits());
    }

@GetMapping("/{id}")
    public ResponseEntity <Produit> getProduitById(@PathVariable Long id){
    return ResponseEntity.ok(produitService.getProduitById(id));
}
 @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduit(@PathVariable Long id){
    produitService.deleteProduit(id);
    return ResponseEntity.ok("Produit supprimé avec succés");
}

@GetMapping("/low-stock")
    public ResponseEntity<List<Produit>> getLowStockProducts(){
    return ResponseEntity.ok(produitService.getLowStockProducts()) ;
}

@GetMapping("/price/{price}")
    public ResponseEntity<List<Produit>> getByPrixInferieur(@PathVariable Double price){
    return ResponseEntity.ok(produitService.getProduitsByPrixInferieur(price));
}

@GetMapping("/category/{category}")
public ResponseEntity<List<Produit>> getByCategorie(@PathVariable String category) {
    return ResponseEntity.ok(produitService.getProduitsByCategorie(category));
}




}
