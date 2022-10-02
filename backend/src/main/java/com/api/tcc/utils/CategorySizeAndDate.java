/*
 * Copyright (c) 2022 created by student Murilo de Paula Araujo from the Computer Engineering
 * course at Pontifical Catholic University of Campinas (PUC-Campinas).
 * All rights reserved.
 */
package com.api.tcc.utils;

import java.time.LocalDate;

/**
 * @author Murilo de Paula Araujo
 */

public class CategorySizeAndDate {
    private String category;
    private String size;
    private LocalDate date;

    public CategorySizeAndDate(String category, String size, LocalDate date) {
        FormattingDates formattingDates = new FormattingDates();
        this.category = category;
        this.size = size;
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}