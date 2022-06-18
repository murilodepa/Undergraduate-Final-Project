/*
 * Copyright (c) 2022 created by student Murilo de Paula Araujo from the Computer Engineering
 * course at Pontifical Catholic University of Campinas (PUC-Campinas).
 * All rights reserved.
 */
package com.api.tcc.database.dtos;

import lombok.Data;

import java.util.UUID;

/**
 * @author Murilo de Paula Araujo
 */
@Data
public class PurchaseProductDTO {
    private UUID id_purchase;
    private UUID id_product;
}
