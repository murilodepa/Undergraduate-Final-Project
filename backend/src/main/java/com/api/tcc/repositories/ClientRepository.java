package com.api.tcc.repositories;

import com.api.tcc.database.Models.ClientModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<ClientModel, Long> {
}
