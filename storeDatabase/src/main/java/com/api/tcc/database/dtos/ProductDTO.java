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
public class ProductDTO {
    @Size(min = 3, max = 50)
    private String description;
    @Size(min = 3, max = 50)
    private String category;
    @Size(min = 1, max = 5)
    private String size;
    @Size(min = 3, max = 15)
    private String brand;
    private Double price;
    private int quantity;
}
