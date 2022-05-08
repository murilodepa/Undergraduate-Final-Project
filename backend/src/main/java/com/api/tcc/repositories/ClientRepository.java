package com.api.tcc.repositories;

import com.api.tcc.database.Models.ClientModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<ClientModel, UUID> {

    ClientModel findByName(String name);

    boolean existsByCpf(String cpf);
}
