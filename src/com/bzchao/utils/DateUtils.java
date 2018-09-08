package com.bzchao.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 时间转换工具类
 */
public class DateUtils {
    public static Date getDateByCTS(String str) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
        return sdf.parse(str);
    }

    public static String getStringByCTS(String str) throws ParseException {
        Date date = getDateByCTS(str);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    public static void main(String[] args) throws ParseException {
        String dateStr = "Thu Aug 27 18:05:49 CST 2015";
        Date date = getDateByCTS(dateStr);
        System.out.println(date);
    }
}
