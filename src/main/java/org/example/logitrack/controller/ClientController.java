package org.example.logitrack.controller;
import org.example.logitrack.entity.Client;
import org.example.logitrack.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService ;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")

    public ResponseEntity<Client> addClient(@RequestBody Client client) {
        Client saved = clientService.addClient(client);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','AGENT')")

    public ResponseEntity<List<Client>> getAllClients(){
    return ResponseEntity.ok(clientService.getAllClients());
}

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','AGENT')")

    public ResponseEntity <Client> getClientById(@PathVariable Long id){
        return ResponseEntity.ok(clientService.getClientById(id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")

    public ResponseEntity<String> deleteClient(@PathVariable Long id){
        clientService.deleteClient(id);
        return ResponseEntity.ok("Client supprimé avec succés");
    }











}
