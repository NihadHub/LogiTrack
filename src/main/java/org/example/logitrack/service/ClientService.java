package org.example.logitrack.service;
import org.example.logitrack.entity.Client;
import org.example.logitrack.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;

    public Client addClient(Client client){
        return clientRepository.save(client);
    }

    public Page<Client> getAllClients(Pageable pageable){
        return clientRepository.findAll(pageable);
    }

    public Client getClientById(Long id){
        return clientRepository.findById(id).orElseThrow(() -> new RuntimeException("Client introuvable avec l'id: "+id));
    }

    public void deleteClient(Long id){
        if(!clientRepository.existsById(id)){
            throw new RuntimeException("Client introuvable avec l'id : " + id);
        }
        clientRepository.deleteById(id);
    }

    @Transactional
    public Client ajouterPointFidilite(Long id, int points){
        if(points<1 || points>100){
            throw new RuntimeException("les points doivent etre entre 1 et 100");
        }

        Client client = clientRepository.findById(id).orElseThrow(() -> new RuntimeException("Client introuvable avec l'id: "+id));
        client.setPoints(client.getPoints() + points);
        if (client.getPoints() > 100){
            client.setPoints(100);
        }
        return clientRepository.save(client);
    }
}