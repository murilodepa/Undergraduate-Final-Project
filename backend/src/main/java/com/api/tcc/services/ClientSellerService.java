package com.api.tcc.services;

import com.api.tcc.database.Models.ClientModel;
import com.api.tcc.database.Models.ClientSellerModel;
import com.api.tcc.repositories.ClientSellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClientSellerService {

    @Autowired
    ClientSellerRepository clientSellerRepository;

    @Transactional
    public ClientSellerModel save(ClientSellerModel clientSellerModel) {
        return clientSellerRepository.save(clientSellerModel);
    }

    public List<ClientSellerModel> findAll() {
        return clientSellerRepository.findAll();
    }

    public Optional<ClientSellerModel> findById(UUID id) {
        return clientSellerRepository.findById(id);
    }

    @Transactional
    public void delete(ClientSellerModel clientSellerModel) {
        clientSellerRepository.delete(clientSellerModel);
    }
}
