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
import java.time.LocalDate;

/**
 * @author Murilo de Paula Araujo
 */
@Entity
@Getter
@Setter
@Table(name = "TB_CLIENT")
public class ClientModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_client", updatable = false, unique = true, nullable = false)
    private long id;
    @Column(name = "name")
    @Size(min = 3, max = 100)
    private String name;
    @Column(name = "gender")
    @Size(min = 1, max = 10)
    private String gender;
    @Column(name = "birth")
    private LocalDate birth;
    @Column(name = "purchase_suggestion")
    @Size(min = 3, max = 20)
    private String purchaseSuggestion;
}