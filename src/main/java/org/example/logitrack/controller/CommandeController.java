package org.example.logitrack.controller;
import org.example.logitrack.dto.AddProductRequest;
import org.example.logitrack.dto.CreateCommandeRequest;
import org.example.logitrack.entity.Commande;
import org.example.logitrack.entity.Statut;
import org.example.logitrack.service.CommandeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class CommandeController {
    private final CommandeService commandeService;
    @PostMapping
    public ResponseEntity<Commande> createCommande(@RequestBody CreateCommandeRequest request) {
        Commande commande = commandeService.createCommande(request.getClientId());
        return new ResponseEntity<>(commande, HttpStatus.CREATED);
    }

    @PostMapping("/{orderId}/products")
    public ResponseEntity<Commande> addProductToCommande(
            @PathVariable Long orderId,
            @RequestBody AddProductRequest request) {
        Commande commande = commandeService.addProductToCommande(orderId, request);
        return ResponseEntity.ok(commande);
    }

    @GetMapping
    public ResponseEntity<List<Commande>> getAllCommandes() {
        return ResponseEntity.ok(commandeService.getAllCommandes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Commande> getCommandeById(@PathVariable Long id) {
        return ResponseEntity.ok(commandeService.getCommandeById(id));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Commande> updateStatut(
            @PathVariable Long id,
            @RequestBody Map<String, String> body) {
        Statut nouveauStatut = Statut.valueOf(body.get("statut"));
        Commande commande = commandeService.updateStatut(id, nouveauStatut);
        return ResponseEntity.ok(commande);
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<Commande>> getCommandesByClientId(@PathVariable Long clientId) {
        return ResponseEntity.ok(commandeService.getCommandesByClientId(clientId));
    }

    @GetMapping("/count")
    public ResponseEntity<Long> countCommandes() {
        return ResponseEntity.ok(commandeService.countAllCommandes());
    }
}
