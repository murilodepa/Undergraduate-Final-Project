/*
 * Copyright (c) 2022 created by student Murilo de Paula Araujo from the Computer Engineering
 * course at Pontifical Catholic University of Campinas (PUC-Campinas).
 * All rights reserved.
 */
package com.api.tcc.services;

import com.api.tcc.database.Models.PurchaseProductModel;
import com.api.tcc.repositories.PurchaseProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Murilo de Paula Araujo
 */
@Service
public class PurchaseProductService {

    @Autowired
    private PurchaseProductRepository purchaseProductRepository;

    @Transactional
    public PurchaseProductModel save(PurchaseProductModel purchaseProductModel) {
        return purchaseProductRepository.save(purchaseProductModel);
    }

    public List<PurchaseProductModel> findAll() {
        return purchaseProductRepository.findAll();
    }

    public Optional<PurchaseProductModel> findById(UUID id) {
        return purchaseProductRepository.findById(id);
    }

    @Transactional
    public void delete(PurchaseProductModel purchaseProductModel) {
        purchaseProductRepository.delete(purchaseProductModel);
    }
}

