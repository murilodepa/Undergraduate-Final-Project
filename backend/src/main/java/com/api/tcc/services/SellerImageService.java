package com.api.tcc.services;

import com.api.tcc.database.Models.SellerImageModel;
import com.api.tcc.database.Models.SellerModel;
import com.api.tcc.repositories.SellerImageRepository;
import com.api.tcc.repositories.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class SellerImageService {

    @Autowired
    SellerImageRepository sellerImageRepository;

    @Autowired
    SellerRepository sellerRepository;
    List<SellerModel> sellerModel;

    @Transactional
    public SellerImageModel saveImage(byte[] encodedImage, long foreignKey) throws Exception {
        SellerImageModel sellerImageModel = new SellerImageModel();
        sellerImageModel.setImage(encodedImage);
        sellerImageModel.setSellerModel(sellerRepository.findById(foreignKey).orElseThrow(Exception::new));
        return sellerImageRepository.save(sellerImageModel);
    }

    public long lastId() {
        sellerModel = sellerRepository.findAll();
        if(sellerModel.size() > 0) {
            return sellerModel.get((sellerModel.size() - 1)).getId();
        } else {
            return (-1);
        }
    }
}
