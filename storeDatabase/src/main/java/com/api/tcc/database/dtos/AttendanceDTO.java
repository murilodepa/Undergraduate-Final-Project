/*
 * Copyright (c) 2022 created by student Murilo de Paula Araujo from the Computer Engineering
 * course at Pontifical Catholic University of Campinas (PUC-Campinas).
 * All rights reserved.
 */
package com.api.tcc.database.dtos;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Murilo de Paula Araujo
 */
@Data
public class AttendanceDTO {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private long clientId;
    private long sellerId;
}
