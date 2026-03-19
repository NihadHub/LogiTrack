package org.example.logitrack.service;
import org.example.logitrack.entity.Produit;
import org.example.logitrack.repository.ProduitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProduitService {
    private final ProduitRepository produitRepository;

    public Produit addProduit(Produit produit){
        return produitRepository.save(produit);
    }
    public List<Produit> getAllProduits(){
        return produitRepository.findAll();
    }
    public Produit getProduitById(Long id){
        return produitRepository.findById(id).orElseThrow(()-> new RuntimeException("Produit introuvable avec l'id: "+id));
    }
    public void deleteProduit(Long id){
        if(!produitRepository.existsById(id)){
            throw new RuntimeException("Produit introuvable avec l'id : \" + id");
        }
        produitRepository.deleteById(id);
    }

    public List<Produit>  getProduitsByCategorie(String categorie){
        return produitRepository.findProduitByCategorie(categorie);
    }

    public List<Produit> getProduitsByPrixInferieur(double prix){
        return produitRepository.findByPrixLessThan(prix);
    }

    public List<Produit> getLowStockProducts(){
        return produitRepository.findLowStockProducts(15);
    }
    public Produit getMostOrderedProduct(){
        List<Produit> produits = produitRepository.findMostOrderedProducts();
        if(produits.isEmpty()){
            throw new RuntimeException("Aucun produit commandé trouvé");
        }
        return produits.get(0);
    }
}
