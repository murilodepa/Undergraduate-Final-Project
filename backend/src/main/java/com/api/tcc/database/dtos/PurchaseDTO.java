/*
 * Copyright (c) 2022 created by student Murilo de Paula Araujo from the Computer Engineering
 * course at Pontifical Catholic University of Campinas (PUC-Campinas).
 * All rights reserved.
 */
package com.api.tcc.database.dtos;

import lombok.Data;

/**
 * @author Murilo de Paula Araujo
 */
@Data
public class PurchaseDTO {
    private long id_client;
    private String category;
}
