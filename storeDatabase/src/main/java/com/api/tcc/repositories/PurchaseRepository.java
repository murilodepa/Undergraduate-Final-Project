/*
 * Copyright (c) 2022 created by student Murilo de Paula Araujo from the Computer Engineering
 * course at Pontifical Catholic University of Campinas (PUC-Campinas).
 * All rights reserved.
 */
package com.api.tcc.repositories;

import com.api.tcc.database.Models.PurchaseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * @author Murilo de Paula Araujo
 */
@Repository
public interface PurchaseRepository extends JpaRepository<PurchaseModel, UUID> {
}
