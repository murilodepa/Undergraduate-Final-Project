package com.api.tcc.services;

import com.api.tcc.database.Models.SellerModel;
import com.api.tcc.repositories.SellerRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SellerService {

    private final SellerRepository sellerRepository;

    public SellerService(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    @Transactional
    public SellerModel save(SellerModel sellerModel) {
        return sellerRepository.save(sellerModel);
    }

    public List<SellerModel> findAll() {
        return sellerRepository.findAll();
    }

    public Optional<SellerModel> findById(UUID id) {
        return sellerRepository.findById(id);
    }

    @Transactional
    public void delete(SellerModel sellerModel) {
        sellerRepository.delete((sellerModel));
    }

    public boolean existsByCpf(String cpf) {
        return sellerRepository.existsByCpf(cpf);
    }

    public UUID getIdByCpf(String searchCpf) {
        return sellerRepository.findByCpf(searchCpf).getId();
    }
}
