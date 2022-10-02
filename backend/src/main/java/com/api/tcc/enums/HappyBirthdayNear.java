/*
 * Copyright (c) 2022 created by student Murilo de Paula Araujo from the Computer Engineering
 * course at Pontifical Catholic University of Campinas (PUC-Campinas).
 * All rights reserved.
 */
package com.api.tcc.enums;

/**
 * @author Murilo de Paula Araujo
 */

public enum HappyBirthdayNear {
    INVISIBLE('0'),
    VISIBLE('1'),
    PURCHASED('2');

    private final int happyBirthday;

    HappyBirthdayNear(char happyBirthday) {
        this.happyBirthday = Character.getNumericValue(happyBirthday);
    }

    public int getHappyBirthdayNear() {
        return happyBirthday;
    }
}