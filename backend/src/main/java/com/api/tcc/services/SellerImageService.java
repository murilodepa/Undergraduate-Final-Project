/*
 * Copyright (c) 2022 created by student Murilo de Paula Araujo from the Computer Engineering
 * course at Pontifical Catholic University of Campinas (PUC-Campinas).
 * All rights reserved.
 */
package com.api.tcc.services;

import com.api.tcc.database.Models.SellerImageModel;
import com.api.tcc.database.Models.SellerModel;
import com.api.tcc.database.dtos.IdImageNameDTO;
import com.api.tcc.repositories.SellerImageRepository;
import com.api.tcc.repositories.SellerRepository;
import com.api.tcc.utils.ManipulatingImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Murilo de Paula Araujo
 */
@Service
public class SellerImageService {
    @Autowired
    private SellerImageRepository sellerImageRepository;

    @Autowired
    private SellerRepository sellerRepository;

    List<SellerModel> sellerModel;

    @Transactional
    public SellerImageModel saveImage(byte[] encodedImage, long foreignKey) throws Exception {
        SellerImageModel sellerImageModel = new SellerImageModel();
        sellerImageModel.setImage(encodedImage);
        sellerImageModel.setSellerModel(sellerRepository.findById(foreignKey).orElseThrow(Exception::new));
        return sellerImageRepository.save(sellerImageModel);
    }

    public long getSellerId() {
        sellerModel = sellerRepository.findAll();
        if(sellerModel.size() > 0) {
            return sellerModel.get((sellerModel.size() - 1)).getId();
        } else {
            return (-1);
        }
    }

    public List<SellerImageModel> findSellerImages(long id) {
        return sellerImageRepository.findSellerImages(id);
    }
    public List<IdImageNameDTO> findSellerIdImagesNamesList(List<SellerModel> sellerModelList) throws IOException {
        List<IdImageNameDTO> IdImageNameDTOList = new ArrayList<IdImageNameDTO>();

        ManipulatingImage manipulatingImage = new ManipulatingImage();
        for(SellerModel sellerModelAux: sellerModelList) {
            List<SellerImageModel> sellerImageModelList = sellerImageRepository.findSellerImages(sellerModelAux.getId());
            if(!sellerImageModelList.isEmpty()) {
                IdImageNameDTO idImageNameDTO = new IdImageNameDTO();
                idImageNameDTO.setUserId(sellerModelAux.getId());
                idImageNameDTO.setName(sellerModelAux.getName());
                idImageNameDTO.setProfileImage(manipulatingImage.decodeImage(sellerImageModelList.get(0).getImage()));
                IdImageNameDTOList.add(idImageNameDTO);
            } else {
                return null;
            }
        }
        return IdImageNameDTOList;
    }

    @Transactional
    public void deleteSellerImage(long sellerId) {
        List<SellerImageModel> sellerImageModelList = sellerImageRepository.findSellerImages(sellerId);
        if(!sellerImageModelList.isEmpty()) {
            sellerImageRepository.deleteAll(sellerImageModelList);
        }
    }
/*
    @DeleteMapping("/removeSeller/{id}")
    public ResponseEntity<Object> deleteSeller(@PathVariable(value = "id") long id) {
        Optional<SellerModel> sellerModelOptional = sellerService.findById(id);
        if (!sellerModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found!");
        }
        sellerService.delete(sellerModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Client delected successfully!");
    }*/
}
