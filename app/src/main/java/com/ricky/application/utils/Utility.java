package com.ricky.application.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class Utility {

    public static String convertDate(String dateInput) {
        SimpleDateFormat inputFormat = new SimpleDateFormat(Constant.ISO_DATE_FORMAT);
        SimpleDateFormat outputFormat = new SimpleDateFormat(Constant.SIMPLE_DATE_TIME_FORMAT);
        Date date = null;
        try {
            date = inputFormat.parse(dateInput);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return outputFormat.format(date);
    }

    private Utility() {
    }

}
