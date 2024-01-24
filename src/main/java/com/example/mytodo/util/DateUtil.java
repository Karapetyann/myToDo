package com.example.mytodo.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    public  static String dateToString(Date date) {
        return SIMPLE_DATE_FORMAT.format(date);
    }
    public static Date stringToDate(String strDate) throws ParseException {
        return SIMPLE_DATE_FORMAT.parse(strDate);
    }
}
