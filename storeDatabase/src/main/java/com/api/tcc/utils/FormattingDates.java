/*
 * Copyright (c) 2022 created by student Murilo de Paula Araujo from the Computer Engineering
 * course at Pontifical Catholic University of Campinas (PUC-Campinas).
 * All rights reserved.
 */
package com.api.tcc.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

/**
 * @author Murilo de Paula Araujo
 */
public class FormattingDates {

    public LocalDate convertDateToDatabase(final String date) throws ParseException {
        Date inSDF = new SimpleDateFormat("dd/MM/yyyy").parse(date);
        String outSDF = new SimpleDateFormat("yyyy-MM-dd").format(inSDF);
        return LocalDate.parse(outSDF);
    }
}