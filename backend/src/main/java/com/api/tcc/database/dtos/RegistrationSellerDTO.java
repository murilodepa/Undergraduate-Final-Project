/*
 * Copyright (c) 2022 created by student Murilo de Paula Araujo from the Computer Engineering
 * course at Pontifical Catholic University of Campinas (PUC-Campinas).
 * All rights reserved.
 */
package com.api.tcc.database.dtos;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

/**
 * @author Murilo de Paula Araujo
 */

@Data
public class RegistrationSellerDTO {
    private long id;
    @Size(min = 3, max = 100)
    private String name;
    @Size(min = 1, max = 10)
    private String gender;
    private int birth;
    @Size(min = 3, max = 50)
    private String sector;
    private Boolean available;
    private int attendances;
    @Email
    private String email;
    private String profileImage;
}