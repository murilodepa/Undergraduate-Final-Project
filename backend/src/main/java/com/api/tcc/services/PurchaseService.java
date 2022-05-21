package com.api.tcc.services;

import com.api.tcc.database.Models.ClientSellerModel;
import com.api.tcc.database.Models.PurchaseModel;
import com.api.tcc.repositories.PurchaseRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;

    public PurchaseService(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    @Transactional
    public PurchaseModel save(PurchaseModel purchaseModel) {
        return purchaseRepository.save(purchaseModel);
    }

    public List<PurchaseModel> findAll() {
        return purchaseRepository.findAll();
    }

    public Optional<PurchaseModel> findById(UUID id) {
        return purchaseRepository.findById(id);
    }

    @Transactional
    public void delete(PurchaseModel purchaseModel) {
        purchaseRepository.delete(purchaseModel);
    }
}