package com.api.tcc.repositories;

import com.api.tcc.database.Models.PurchaseProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PurchaseProductRepository extends JpaRepository<PurchaseProductModel, UUID> {
}
