package com.api.tcc.services;

import com.api.tcc.database.Models.ClientModel;
import com.api.tcc.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    public String[] getClientIdsAndNames() {
        String[] idsAndNames = new String[100];
        int i=0;
        List<ClientModel> clientModelList = clientRepository.findAll();

        for(ClientModel clientModel: clientModelList) {
            idsAndNames[i] = String.valueOf(clientModel.getId());
            i++;
            idsAndNames[i] = clientModel.getName();
            i++;
        }
        return idsAndNames;
    }
}
