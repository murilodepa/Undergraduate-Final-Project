package com.api.tcc.services;

import com.api.tcc.database.Models.SellerModel;
import com.api.tcc.repositories.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class SellerService {

    @Autowired
    SellerRepository sellerRepository;

    @Transactional
    public SellerModel save(SellerModel sellerModel) {
        return sellerRepository.save(sellerModel);
    }

    public List<SellerModel> findAll() {
        return sellerRepository.findAll();
    }

    public Optional<SellerModel> findById(long id) {
        return sellerRepository.findById(id);
    }

    @Transactional
    public void delete(SellerModel sellerModel) {
        sellerRepository.delete((sellerModel));
    }
}
