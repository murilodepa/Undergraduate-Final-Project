/*
 * Copyright (c) 2022 created by student Murilo de Paula Araujo from the Computer Engineering
 * course at Pontifical Catholic University of Campinas (PUC-Campinas).
 * All rights reserved.
 */
package com.api.tcc.repositories;

import com.api.tcc.database.Models.SellerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Murilo de Paula Araujo
 */
@Repository
public interface SellerRepository extends JpaRepository<SellerModel, Long> {
    boolean existsByEmail(String email);

    @Query("select q from SellerModel q where q.available = true")
    List<SellerModel> findAllSellerAvailable();
}
