package com.api.tcc.repositories;

import com.api.tcc.database.Models.SellerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SellerRepository extends JpaRepository<SellerModel, UUID> {

    boolean existsByCpf(String cpf);
}
