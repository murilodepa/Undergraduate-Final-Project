package com.api.tcc.repositories;

import com.api.tcc.database.Models.ClientImageModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClientImageRepository extends JpaRepository<ClientImageModel, UUID> {
}
