/*
 * Copyright (c) 2022 created by student Murilo de Paula Araujo from the Computer Engineering
 * course at Pontifical Catholic University of Campinas (PUC-Campinas).
 * All rights reserved.
 */
package com.api.tcc.utils;

import com.api.tcc.enums.AgeGroup;
import com.api.tcc.enums.HappyBirthdayNear;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * @author Murilo de Paula Araujo
 */
public class FormattingDates {

    public LocalDate convertDateToDatabase(final String date) throws ParseException {
        Date inSDF = new SimpleDateFormat("dd/MM/yyyy").parse(date);
        String outSDF = new SimpleDateFormat("yyyy-MM-dd").format(inSDF);
        return LocalDate.parse(outSDF);
    }

    public String convertDatabaseToDateComplete(final LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " - (" + convertDateToAge(date) + " anos)";
    }

    public int convertDateToAge(final LocalDate date) {
        LocalDate today = LocalDate.now(ZoneId.of("America/Sao_Paulo"));
        int age = today.getYear() - date.getYear();
        if (today.getMonthValue() < date.getMonthValue()) {
            age--;
        } else if (today.getMonthValue() == date.getMonthValue() && today.getDayOfMonth() < date.getDayOfMonth()) {
            age--;
        }
        return age;
    }

    public String getAgeGroup(final LocalDate date) {
        int age = convertDateToAge(date);
        if (age < 12) {
            return AgeGroup.CHILD.getAgeGroup();
        }
        if (age <= 18) {
            return AgeGroup.YOUNG.getAgeGroup();
        }
        if (age <= 60) {
            return AgeGroup.ADULT.getAgeGroup();
        }
        return AgeGroup.SENIORS.getAgeGroup();
    }

    public int getHappyBirthdayNear(final LocalDate date, List<CategorySizeAndDate> categorySizeAndDateList) {
        if (!categorySizeAndDateList.isEmpty()) {
            categorySizeAndDateList.sort(Comparator.comparing(CategorySizeAndDate::getDate).reversed());
            LocalDate lastPurchaseDate = categorySizeAndDateList.get(0).getDate();
            if (lastPurchaseDate.getMonthValue() == date.getMonthValue()) {
                return HappyBirthdayNear.PURCHASED.getHappyBirthdayNear();
            }
            if (lastPurchaseDate.getMonthValue() == (date.getMonthValue() - 1) &&
                    lastPurchaseDate.getDayOfMonth() >= date.getDayOfMonth()) {
                return HappyBirthdayNear.PURCHASED.getHappyBirthdayNear();
            }
            if (lastPurchaseDate.getMonthValue() == (date.getMonthValue() + 1) &&
                    lastPurchaseDate.getDayOfMonth() <= date.getDayOfMonth()) {
                return HappyBirthdayNear.PURCHASED.getHappyBirthdayNear();
            }
        }

        LocalDate today = LocalDate.now(ZoneId.of("America/Sao_Paulo"));
        if (today.getMonthValue() == date.getMonthValue()) {
            return HappyBirthdayNear.VISIBLE.getHappyBirthdayNear();
        }
        if (today.getMonthValue() == (date.getMonthValue() - 1) && today.getDayOfMonth() >= date.getDayOfMonth()) {
            return HappyBirthdayNear.VISIBLE.getHappyBirthdayNear();
        }
        if (today.getMonthValue() == (date.getMonthValue() + 1) && today.getDayOfMonth() <= date.getDayOfMonth()) {
            return HappyBirthdayNear.VISIBLE.getHappyBirthdayNear();
        }
        return HappyBirthdayNear.INVISIBLE.getHappyBirthdayNear();
    }
}