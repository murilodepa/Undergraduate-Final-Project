/*
 * Copyright (c) 2022 created by student Murilo de Paula Araujo from the Computer Engineering
 * course at Pontifical Catholic University of Campinas (PUC-Campinas).
 * All rights reserved.
 */
package com.api.tcc.repositories;

import com.api.tcc.database.Models.AttendanceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Murilo de Paula Araujo
 */
@Repository
public interface AttendanceRepository extends JpaRepository<AttendanceModel, UUID> {

    Optional<AttendanceModel> findClientSellerModelByClientModel_IdAndEndTime(long clientModel_id, LocalDateTime endTime);

    Optional<List<AttendanceModel>> findClientSellerModelByClientModel_Id(long id_client);

    Optional<AttendanceModel> findClientSellerModelBySellerModel_Id(long sellerModel_id);
}
