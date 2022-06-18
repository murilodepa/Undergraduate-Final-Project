/*
 * Copyright (c) 2022 created by student Murilo de Paula Araujo from the Computer Engineering
 * course at Pontifical Catholic University of Campinas (PUC-Campinas).
 * All rights reserved.
 */
package com.api.tcc.database.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

/**
 * @author Murilo de Paula Araujo
 */
@Entity
@Setter
@Getter
@Table(name = "TB_SELLER")
public class SellerModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_seller", updatable = false, unique = true, nullable = false)
    private long id;
    @Column(name = "name")
    @Size(min = 3, max = 100)
    private String name;
    @Column(name = "gender")
    @Size(min = 1, max = 10)
    private String gender;
    @Column(name = "birth")
    private LocalDate birth;
    @Column(name = "sector")
    @Size(min = 3, max = 20)
    private String sector;
    @Column(name = "available")
    private Boolean available;
    @Column(name = "attendances")
    private int attendances;
    @NotNull
    @Column(name = "email")
    private String email;
    @JsonIgnore
    @Column(name = "password")
    private String password;
}