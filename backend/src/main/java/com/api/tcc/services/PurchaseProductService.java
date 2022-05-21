package com.api.tcc.services;

import com.api.tcc.database.Models.PurchaseProductModel;
import com.api.tcc.repositories.PurchaseProductRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PurchaseProductService {

    private final PurchaseProductRepository purchaseProductRepository;

    public PurchaseProductService(PurchaseProductRepository purchaseProductRepository) {
        this.purchaseProductRepository = purchaseProductRepository;
    }

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

