package com.api.tcc.services;

import com.api.tcc.database.Models.ClientModel;
import com.api.tcc.repositories.ClientRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Transactional
    public ClientModel save(ClientModel clientModel) {
        return clientRepository.save(clientModel);
    }

    public List<ClientModel> findAll() {
        return clientRepository.findAll();
    }

    public Optional<ClientModel> findById(long id) {
        return clientRepository.findById(id);
    }

    @Transactional
    public void delete(ClientModel clientModel) {
        clientRepository.delete(clientModel);
    }
}
