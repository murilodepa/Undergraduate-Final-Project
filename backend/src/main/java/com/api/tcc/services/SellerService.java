/*
 * Copyright (c) 2022 created by student Murilo de Paula Araujo from the Computer Engineering
 * course at Pontifical Catholic University of Campinas (PUC-Campinas).
 * All rights reserved.
 */
package com.api.tcc.services;

import com.api.tcc.database.Models.SellerModel;
import com.api.tcc.email.EmailMessages;
import com.api.tcc.email.SendEmail;
import com.api.tcc.repositories.SellerRepository;
import com.api.tcc.utils.IndexAndName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Murilo de Paula Araujo
 */
@Service
public class SellerService {

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private SendEmail sendEmail;

    @Transactional
    public SellerModel save(SellerModel sellerModel) {
        this.sendEmail.send(sellerModel.getEmail(),
                EmailMessages.createTitle(sellerModel), EmailMessages.messageToNewSeller(sellerModel));
        return sellerRepository.save(sellerModel);
    }

    public List<SellerModel> findAll() {
        return sellerRepository.findAll();
    }

    public List<SellerModel> findAllSellerAvailable() {
        return sellerRepository.findAllSellerAvailable();
    }

    public Optional<SellerModel> findById(long id) {
        return sellerRepository.findById(id);
    }

    @Transactional
    public void delete(SellerModel sellerModel) {
        this.sendEmail.send(sellerModel.getEmail(),
                EmailMessages.createTitleToRemove(sellerModel), EmailMessages.messageToRemoveSeller(sellerModel));
        sellerRepository.delete((sellerModel));
    }

    public boolean existsByEmail(String email) {
        return sellerRepository.existsByEmail(email);
    }

    public List<IndexAndName> getSellerNames() {
        List<IndexAndName> sellerDate = new ArrayList<>();
        List<SellerModel> sellerModelList = sellerRepository.findAll();

        for (SellerModel sellerModel : sellerModelList) {
            sellerDate.add(new IndexAndName(sellerModel.getId(), sellerModel.getName()));
        }
        return sellerDate;
    }
}
