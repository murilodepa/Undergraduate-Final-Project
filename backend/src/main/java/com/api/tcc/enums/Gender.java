/*
 * Copyright (c) 2022 created by student Murilo de Paula Araujo from the Computer Engineering
 * course at Pontifical Catholic University of Campinas (PUC-Campinas).
 * All rights reserved.
 */
package com.api.tcc.enums;

/**
 * @author Murilo de Paula Araujo
 */

public enum Gender {
    FEMININE('0'),
    MASCULINE('1'),
    OTHERS('0');

    private final char gender;

    Gender(char gender) {
        this.gender = gender;
    }

    public char getGender() {
        return gender;
    }
}