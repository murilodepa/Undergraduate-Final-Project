/*
 * Copyright (c) 2022 created by student Murilo de Paula Araujo from the Computer Engineering
 * course at Pontifical Catholic University of Campinas (PUC-Campinas).
 * All rights reserved.
 */
package com.api.tcc.database.Models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.UUID;

/**
 * @author Murilo de Paula Araujo
 */
@Entity
@Setter
@Getter
@Table(name = "TB_PRODUCT")
public class ProductModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_purchase", updatable = false, unique = true, nullable = false)
    private UUID id;
    @Column(name = "description")
    @Size(min = 3, max = 50)
    private String description;
    @Column(name = "category")
    @Size(min = 3, max = 50)
    private String category;
    @Column(name = "size")
    @Size(min = 1, max = 5)
    private String size;
    @Column(name = "brand")
    @Size(min = 3, max = 15)
    private String brand;
    @Column(name = "price")
    private Double price;
    @Column(name = "quantity")
    private int quantity;
}