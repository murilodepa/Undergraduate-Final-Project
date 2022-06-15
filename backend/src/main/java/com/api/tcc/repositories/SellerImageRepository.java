package com.api.tcc.repositories;

import com.api.tcc.database.Models.SellerImageModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SellerImageRepository extends JpaRepository<SellerImageModel, UUID> {
    @Query("select q from SellerImageModel q where q.sellerModel.id =:id_seller")
    List<SellerImageModel> findSellerImages(@Param("id_seller") long id_seller);
}
