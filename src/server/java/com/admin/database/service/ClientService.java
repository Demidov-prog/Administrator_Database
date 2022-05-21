package com.admin.database.service;


import com.admin.database.entity.Client;
import com.admin.database.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> getAll() {
        return clientRepository.findAll();
    }

    public void save(Client client) {
        clientRepository.save(client);
    }

    public void delete(Long id) { clientRepository.deleteById(id);}

    public List<Client> filter(
            String firstName, String lastName, String telephone, String trainerName) {

        return clientRepository.findAllClientsByFirstNameAndLastNameAndTelephoneAndTrainer(
                firstName, lastName, telephone, trainerName
        );
    }

    public Client findById(Long id) {
        return clientRepository.findById(id).orElse(null);
    }
}

