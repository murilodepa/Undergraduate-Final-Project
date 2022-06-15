package com.api.tcc.repositories;

import com.api.tcc.database.Models.ClientSellerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ClientSellerRepository extends JpaRepository<ClientSellerModel, UUID> {
    @Query("select q from ClientSellerModel q where q.clientModel.id =:id_client and q.endTime =:null")
    ClientSellerModel findClientAttendances(@Param("id_client") long id_client);

    @Query("select q from ClientSellerModel q where q.sellerModel.id =:id_seller and q.endTime =:null")
    ClientSellerModel findSellerAttendances(@Param("id_seller") long id_seller);
}
