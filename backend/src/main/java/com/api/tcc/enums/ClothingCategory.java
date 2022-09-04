/*
 * Copyright (c) 2022 created by student Murilo de Paula Araujo from the Computer Engineering
 * course at Pontifical Catholic University of Campinas (PUC-Campinas).
 * All rights reserved.
 */
package com.api.tcc.enums;

/**
 * @author Murilo de Paula Araujo
 */

public enum ClothingCategory {
    INDECISION('0'),
    SHIRT('1'),
    PANTS('2'),
    SHORT('3'),
    DRESS('4');

    private final char category;

    ClothingCategory(final char category) {
        this.category = category;
    }

    public char getCategory() {
        return category;
    }

}