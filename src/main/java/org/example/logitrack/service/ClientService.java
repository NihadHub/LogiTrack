package org.example.logitrack.service;
import org.example.logitrack.entity.Client;
import org.example.logitrack.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;

    public Client addClient(Client client){
        return clientRepository.save(client);
    }
    public List<Client> getAllClients(){
        return clientRepository.findAll();
    }

    public Client getClientById(long id){
        return clientRepository.findById(id).orElseThrow(() -> new RuntimeException("Client introuvable avec l'id: "+id));
    }

    public void deleteClient(long id){
        if(!clientRepository.existsById(id)){
            throw new RuntimeException("Client introuvable avec l'id : " + id);
        }
        clientRepository.deleteById(id);
    }
}
