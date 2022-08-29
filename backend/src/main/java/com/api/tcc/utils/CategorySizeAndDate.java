package com.api.tcc.utils;

import java.text.ParseException;
import java.time.LocalDate;

public class CategorySizeAndDate {
    private String category;
    private String size;
    private String date;

    public CategorySizeAndDate(String category, String size, LocalDate date) {
        FormattingDates formattingDates = new FormattingDates();
        this.category = category;
        this.size = size;
        this.date = formattingDates.convertDatabaseToDate(date);
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}