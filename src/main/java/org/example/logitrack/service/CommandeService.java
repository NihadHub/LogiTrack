package org.example.logitrack.service;
import org.example.logitrack.dto.AddProductRequest;
import org.example.logitrack.entity.*;
import org.example.logitrack.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommandeService {
    private final CommandeRepository commandeRepository;
    private final ClientRepository clientRepository;
    private final ProduitRepository produitRepository;
    private final LigneCommandeRepository ligneCommandeRepository;

    @Transactional
    public Commande createCommande(Long clientId){
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client introuvable avec l'id : " + clientId));
        Commande commande = new Commande();
        commande.setClient(client);
        commande.setDateCommande(LocalDate.now());
        commande.setStatut(Statut.EN_ATTENTE);
        return commandeRepository.save(commande);
    }

    @Transactional
    public Commande addProductToCommande(Long orderId, AddProductRequest request) {
        Commande commande = commandeRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Commande introuvable avec l'id : " + orderId));

        Produit produit = produitRepository.findById(request.getProduitId())
                .orElseThrow(() -> new RuntimeException("Produit introuvable avec l'id : " + request.getProduitId()));

        if (produit.getQuantiteStock() < request.getQuantite()) {
            throw new RuntimeException("Stock insuffisant pour le produit : " + produit.getNom()
                    + " (stock disponible : " + produit.getQuantiteStock() + ")");
        }

        LigneCommande ligne = new LigneCommande();
        ligne.setCommande(commande);
        ligne.setProduit(produit);
        ligne.setQuantite(request.getQuantite());

        produit.setQuantiteStock(produit.getQuantiteStock() - request.getQuantite());
        produitRepository.save(produit);

        ligneCommandeRepository.save(ligne);
        commande.getLignesCommande().add(ligne);

        return commandeRepository.save(commande);
    }

    public List<Commande> getAllCommandes() {
        return commandeRepository.findAll();
    }

    public Commande getCommandeById(Long id) {
        return commandeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Commande introuvable avec l'id : " + id));
    }


    @Transactional
    public Commande updateStatut(Long id, Statut nouveauStatut) {
        Commande commande = commandeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Commande introuvable avec l'id : " + id));

        commande.setStatut(nouveauStatut);
        return commandeRepository.save(commande);
    }

    public List<Commande> getCommandesByClientId(Long clientId) {
        return commandeRepository.findByClientId(clientId);
    }

    public Long countAllCommandes() {
        return commandeRepository.countAllCommandes();
    }
}
