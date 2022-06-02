package com.api.tcc.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class FormattingDates {

    public LocalDate ConvertDateToDatabase(final String date) throws ParseException {
        Date inSDF = new SimpleDateFormat("dd/MM/yyyy").parse(date);
        String outSDF = new SimpleDateFormat("yyyy-MM-dd").format(inSDF);
        return LocalDate.parse(outSDF);
    }
}
