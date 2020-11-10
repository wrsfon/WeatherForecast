package com.example.demo.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.stream.Stream;

public final class DateUtils {
    public static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    public static final String dateToString (Date date) {
        formatter.setCalendar(new GregorianCalendar());
        String strDate = formatter.format(date);

        return strDate;
    }

    public static final Date plusNineDays(String date) {
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(formatter.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        cal.add(Calendar.DATE, 9);
        return cal.getTime();
    }
}
