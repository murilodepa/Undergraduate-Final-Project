package com.api.tcc.repositories;

import com.api.tcc.database.Models.ClientSellerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClientSellerRepository extends JpaRepository<ClientSellerModel, UUID> {
}
