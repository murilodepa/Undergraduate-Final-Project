package com.api.tcc.services;

import com.api.tcc.database.Models.ClientImageModel;
import com.api.tcc.database.Models.ClientModel;
import com.api.tcc.database.Models.SellerImageModel;
import com.api.tcc.database.Models.SellerModel;
import com.api.tcc.database.dtos.IdImageNameDTO;
import com.api.tcc.repositories.ClientImageRepository;
import com.api.tcc.repositories.ClientRepository;
import com.api.tcc.utils.ManipulatingImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
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

    public List<ClientImageModel> findClientImages(long id) {
        return clientImageRepository.findClientImages(id);
    }

    public List<IdImageNameDTO> findClientIdImagesNamesList(List<ClientModel> clientModelList) throws IOException {
        List<IdImageNameDTO> IdImageNameDTOList = new ArrayList<IdImageNameDTO>();

        ManipulatingImage manipulatingImage = new ManipulatingImage();
        for(ClientModel clientModelAux: clientModelList) {
            List<ClientImageModel> clientImageModelList = clientImageRepository.findClientImages(clientModelAux.getId());
            if(!clientImageModelList.isEmpty()) {
                IdImageNameDTO idImageNameDTO = new IdImageNameDTO();
                idImageNameDTO.setUserId(clientModelAux.getId());
                idImageNameDTO.setName(clientModelAux.getName());
                idImageNameDTO.setProfileImage(manipulatingImage.decodeImage(clientImageModelList.get(0).getImage()));
                IdImageNameDTOList.add(idImageNameDTO);
            } else {
                return null;
            }
        }
        return IdImageNameDTOList;
    }

    @Transactional
    public void deleteClientImage(long clientId) {
        List<ClientImageModel> clientImageModelList = clientImageRepository.findClientImages(clientId);
        if(!clientImageModelList.isEmpty()) {
            clientImageRepository.deleteAll(clientImageModelList);
        }
    }
}