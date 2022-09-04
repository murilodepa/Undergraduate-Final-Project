/*
 * Copyright (c) 2022 created by student Murilo de Paula Araujo from the Computer Engineering
 * course at Pontifical Catholic University of Campinas (PUC-Campinas).
 * All rights reserved.
 */
package com.api.tcc.utils;

/**
 * @author Murilo de Paula Araujo
 */

public class NameAndKinship {
    private String name;
    private String kinship;

    public NameAndKinship(String name, String kinship) {
        this.name = name;
        this.kinship = kinship;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKinship() {
        return kinship;
    }

    public void setKinship(String kinship) {
        this.kinship = kinship;
    }
}