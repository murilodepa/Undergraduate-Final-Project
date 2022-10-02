/*
 * Copyright (c) 2022 created by student Murilo de Paula Araujo from the Computer Engineering
 * course at Pontifical Catholic University of Campinas (PUC-Campinas).
 * All rights reserved.
 */
package com.api.tcc.database.dtos;

import lombok.Data;

import javax.validation.constraints.Size;

/**
 * @author Murilo de Paula Araujo
 */

@Data
public class ClientsWaitingAttendanceDTO {
    private long clientId;
    @Size(min = 3, max = 100)
    private String name;
    @Size(min = 1, max = 10)
    private String gender;
    private String birth;
    private String profileImage;
    private int visibleHappyBirthdayImage;
}
