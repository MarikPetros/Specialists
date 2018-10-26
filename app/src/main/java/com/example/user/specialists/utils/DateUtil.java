package com.example.user.specialists.utils;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public final class DateUtil {
    public static String formatDateStile(String birthDate) {
        Date date =getDateFromText(birthDate);
        return getTextFromDate(date);
    }

    public static Date getDate(String birthDate){
        return getDateFromText(birthDate);
    }

    private static Date getDateFromText(String birthDate){
        Date date = new Date();

        //from String to Date
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        try {
            date = format.parse(birthDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }

    private static String getTextFromDate(Date date){

        // From Date to String
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());

        String dateTime = dateFormat.format(date);

        return dateTime + " г.";
    }
}
