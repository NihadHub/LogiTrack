package org.example.logitrack.repository;
import org.example.logitrack.entity.Commande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommandeRepository extends JpaRepository<Commande,Long>{
    List<Commande> findByClientId(Long clientId);
    @Query("select count(c) from Commande c")
    Long countAllCommandes();
}
