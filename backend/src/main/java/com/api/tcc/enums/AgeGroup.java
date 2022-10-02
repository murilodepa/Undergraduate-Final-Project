/*
 * Copyright (c) 2022 created by student Murilo de Paula Araujo from the Computer Engineering
 * course at Pontifical Catholic University of Campinas (PUC-Campinas).
 * All rights reserved.
 */
package com.api.tcc.enums;

/**
 * @author Murilo de Paula Araujo
 */

public enum AgeGroup {
    CHILD("crianca"),
    YOUNG("jovem"),
    ADULT("adulto"),
    SENIORS("idoso");

    private final String ageGroup;

    AgeGroup(String ageGroup) {
        this.ageGroup = ageGroup;
    }

    public String getAgeGroup() {
        return ageGroup;
    }
}