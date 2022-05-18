package com.api.tcc.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ManipulatingDates {

    public Date ConvertDateToDatabase(final String date) throws ParseException {
        SimpleDateFormat inSDF = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat outSDF = new SimpleDateFormat("yyyy-MM-dd");
        Date formatterDate = inSDF.parse(date);
        return outSDF.parse(outSDF.format(formatterDate));
    }
}
