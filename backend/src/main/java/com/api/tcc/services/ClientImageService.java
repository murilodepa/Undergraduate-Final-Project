package com.api.tcc.services;

import com.api.tcc.database.Models.ClientImageModel;
import com.api.tcc.database.Models.ClientModel;
import com.api.tcc.repositories.ClientImageRepository;
import com.api.tcc.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ClientImageService {

    @Autowired
    private ClientImageRepository clientImageRepository;

    @Autowired
    private ClientRepository clientRepository;
    List<ClientModel> clientModel;

    @Transactional
    public ClientImageModel saveImage(byte[] encodedImage, long foreignKey) throws Exception {
        ClientImageModel clientImageModel = new ClientImageModel();
        clientImageModel.setImage(encodedImage);
        clientImageModel.setClientModel(clientRepository.findById(foreignKey).orElseThrow(Exception::new));
        return clientImageRepository.save(clientImageModel);
    }

    public long getClientId() {
        clientModel = clientRepository.findAll();
        if (clientModel.size() > 0) {
            return clientModel.get((clientModel.size() - 1)).getId();
        } else {
            return (-1);
        }
    }

    public Optional<List<ClientImageModel>> findClientImages(long id) {
        return clientImageRepository.findClientImages(id);
    }
}