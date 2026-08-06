package org.example.logitrack.controller;
import org.example.logitrack.dto.AddProductRequest;
import org.example.logitrack.dto.CreateCommandeRequest;
import org.example.logitrack.entity.Commande;
import org.example.logitrack.entity.Statut;
import org.example.logitrack.service.CommandeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class CommandeController {
    private final CommandeService commandeService;
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public ResponseEntity<Commande> createCommande(@RequestBody CreateCommandeRequest request) {
        Commande commande = commandeService.createCommande(request.getClientId());
        return new ResponseEntity<>(commande, HttpStatus.CREATED);
    }

    @PostMapping("/{orderId}/products")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public ResponseEntity<Commande> addProductToCommande(
            @PathVariable Long orderId,
            @RequestBody AddProductRequest request) {
        Commande commande = commandeService.addProductToCommande(orderId, request);
        return ResponseEntity.ok(commande);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','AGENT')")
    public ResponseEntity<Page<Commande>> getAllCommandes(
            @PageableDefault(size = 10, sort = "dateCommande") Pageable pageable) {
        return ResponseEntity.ok(commandeService.getAllCommandes(pageable));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','AGENT')")
    public ResponseEntity<Commande> getCommandeById(@PathVariable Long id) {
        return ResponseEntity.ok(commandeService.getCommandeById(id));
    }

    @PutMapping("/{id}/status")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','AGENT')")
    public ResponseEntity<Commande> updateStatut(
            @PathVariable Long id,
            @RequestBody Map<String, String> body) {
        Statut nouveauStatut = Statut.valueOf(body.get("statut"));
        Commande commande = commandeService.updateStatut(id, nouveauStatut);
        return ResponseEntity.ok(commande);
    }

    @GetMapping("/client/{clientId}")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','AGENT')")
    public ResponseEntity<List<Commande>> getCommandesByClientId(@PathVariable Long clientId) {
        return ResponseEntity.ok(commandeService.getCommandesByClientId(clientId));
    }

    @GetMapping("/count")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public ResponseEntity<Long> countCommandes() {
        return ResponseEntity.ok(commandeService.countAllCommandes());
    }
}
