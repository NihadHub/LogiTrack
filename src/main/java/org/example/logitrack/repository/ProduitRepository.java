package org.example.logitrack.repository;

import org.example.logitrack.entity.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface ProduitRepository extends JpaRepository<Produit,Long> {
    List<Produit> findProduitByCategorie(String categorir);
    List<Produit> findByPrixLessThan(double prix);
    @Query("select p from Produit p where p.quantiteStock<:seuil")
    List<Produit> findLowStockProducts(@Param("seuil")int seuil);

    @Query("SELECT lc.produit from LigneCommande lc GROUP BY lc.produit order by sum (lc.quantite)DESC ")
    List<Produit> findMostOrderedProducts();
}
