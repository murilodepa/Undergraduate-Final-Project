/*
 * Copyright (c) 2022 created by student Murilo de Paula Araujo from the Computer Engineering
 * course at Pontifical Catholic University of Campinas (PUC-Campinas).
 * All rights reserved.
 */
package com.api.tcc.database.Models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

/**
 * @author Murilo de Paula Araujo
 */
@Entity
@Setter
@Getter
@Table(name = "TB_PURCHASE")
public class PurchaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_purchase", updatable = false, unique = true, nullable = false)
    private UUID id;
    @Column(name = "category")
    private String category;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "size")
    private String size;
    @Column(name = "kinship")
    private String kinship;
    @Column(name = "persons_name")
    private String personsName;
    @Column(name = "date")
    private LocalDate date;
    @ManyToOne
    @JoinColumn(name = "id_client")
    private ClientModel clientModel;
}
