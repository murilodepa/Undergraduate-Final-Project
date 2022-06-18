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
public class ImageAndNamesDTO {
    @Size(min = 3, max = 100)
    private String name;
    private String profileImage;
}
