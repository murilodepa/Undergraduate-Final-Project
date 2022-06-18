/*
 * Copyright (c) 2022 created by student Murilo de Paula Araujo from the Computer Engineering
 * course at Pontifical Catholic University of Campinas (PUC-Campinas).
 * All rights reserved.
 */
package com.api.tcc.database.Models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

/**
 * @author Murilo de Paula Araujo
 */
@Entity
@Setter
@Getter
@Table(name = "TB_SELLER_IMAGE")
public class SellerImageModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_seller_image", updatable = false, unique = true, nullable = false)
    private UUID id;
    @Lob
    @Type(type = "org.hibernate.type.ImageType")
    @Column(name = "image")
    private byte[] image;
    @ManyToOne
    @JoinColumn(name = "id_seller")
    private SellerModel sellerModel;
}