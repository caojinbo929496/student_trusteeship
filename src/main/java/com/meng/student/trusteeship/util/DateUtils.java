package com.meng.student.trusteeship.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    private static Date convert(Long time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String d = format.format(time);
        try {
            return format.parse(d);
        } catch (ParseException e) {
            throw new IllegalArgumentException("时间转换失败", e);
        }
    }

    private static Date convert(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            String s = sdf.format(date);
            return sdf.parse(s);
        } catch (ParseException e) {
            throw new IllegalArgumentException("日期转换异常");
        }
    }
}
