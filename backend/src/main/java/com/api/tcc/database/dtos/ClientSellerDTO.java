/*
 * Copyright (c) 2022 created by student Murilo de Paula Araujo from the Computer Engineering
 * course at Pontifical Catholic University of Campinas (PUC-Campinas).
 * All rights reserved.
 */
package com.api.tcc.database.dtos;

import com.api.tcc.database.Models.ClientModel;
import com.api.tcc.database.Models.SellerModel;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author Murilo de Paula Araujo
 */
@Data
public class ClientSellerDTO {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private long clientId;
    private long sellerId;
}
