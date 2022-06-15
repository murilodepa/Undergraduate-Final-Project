package com.api.tcc.repositories;

import com.api.tcc.database.Models.SellerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SellerRepository extends JpaRepository<SellerModel, Long> {
    boolean existsByEmail(String email);

    @Query("select q from SellerModel q where q.available = true")
    List<SellerModel> findAllSellerAvailable();
}
