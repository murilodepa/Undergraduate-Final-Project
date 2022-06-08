package com.api.tcc.services;

import com.api.tcc.database.Models.SellerModel;
import com.api.tcc.repositories.SellerRepository;
import com.api.tcc.utils.IndexAndName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SellerService {

    @Autowired
    private SellerRepository sellerRepository;

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

    public boolean existsByEmail(String email) {
        return sellerRepository.existsByEmail(email);
    }

    public List<IndexAndName> getSellerNames() {
        List<IndexAndName> sellerDate = new ArrayList<>();
        List<SellerModel> sellerModelList = sellerRepository.findAll();

        for(SellerModel sellerModel: sellerModelList) {
            sellerDate.add(new IndexAndName(sellerModel.getId(), sellerModel.getName()));
        }
        return sellerDate;
    }
}
