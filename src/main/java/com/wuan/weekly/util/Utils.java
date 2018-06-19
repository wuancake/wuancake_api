package com.wuan.weekly.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    /**
     * 第一周第一天的日期
     */
    public static final Date FIRSTDAY;
    //private static final String SESSION_NAME = "9527";

    static {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date d = null;
        try {
            d = format.parse("2015-10-26 00:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        FIRSTDAY = d;
    }

    public static Integer getMaxWeekNum() {
        return (int) ((new Date().getTime() - FIRSTDAY.getTime()) / (7 * 24 * 60 * 60 * 1000));
    }

}
