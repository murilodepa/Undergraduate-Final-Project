package com.api.tcc.repositories;

import com.api.tcc.database.Models.ClientImageModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClientImageRepository extends JpaRepository<ClientImageModel, UUID> {
    @Query("select q from ClientImageModel q where q.clientModel.id =:id_client")
    Optional<List<ClientImageModel>> findClientImages(@Param("id_client") long id_client);
}
