package com.api.tcc.services;

import com.api.tcc.database.Models.ClientModel;
import com.api.tcc.repositories.ClientRepository;
import com.api.tcc.utils.IndexAndName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

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

    public List<IndexAndName> getClientNames() {
        List<IndexAndName> sellerDate = new ArrayList<>();
        List<ClientModel> clientModelList = clientRepository.findAll();

        for(ClientModel clientModel: clientModelList) {
            sellerDate.add(new IndexAndName(clientModel.getId(), clientModel.getName()));
        }
        return sellerDate;
    }
}
