package com.api.tcc.services;

import com.api.tcc.database.Models.ClientImageModel;
import com.api.tcc.database.Models.ClientModel;
import com.api.tcc.repositories.ClientImageRepository;
import com.api.tcc.repositories.ClientRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ClientImageService {

    private final ClientImageRepository clientImageRepository;
    private final ClientRepository clientRepository;
    List<ClientModel> clientModel;

    public ClientImageService(ClientImageRepository clientImageRepository, ClientRepository clientRepository) {
        this.clientImageRepository = clientImageRepository;
        this.clientRepository = clientRepository;
    }

    @Transactional
    public ClientImageModel saveImage(byte[] encodedImage, long foreignKey) throws Exception {
        ClientImageModel clientImageModel = new ClientImageModel();
        clientImageModel.setImage(encodedImage);
        clientModel = clientRepository.findAll();
        clientImageModel.setClientModel( clientRepository.findById(foreignKey).orElseThrow(Exception::new));
        return clientImageRepository.save(clientImageModel);
    }

    public long lastId() {
        clientModel = clientRepository.findAll();
        if(clientModel.size() > 0) {
            return clientModel.get((clientModel.size() - 1)).getId();
        } else {
            return (-1);
        }
    }
}