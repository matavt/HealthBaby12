package com.matavt.healthbaby12;

import androidx.room.TypeConverter;

import java.util.Date;
import java.util.GregorianCalendar;

public class Converters {
    @TypeConverter
    public static Date fromTimeStamp(Long value){
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }

    @TypeConverter
    public static GregorianCalendar timeStampToGreg(Long value){
        GregorianCalendar calendar = new GregorianCalendar();
        if(value == null) {
            calendar = null;
        } else {
            calendar.setTimeInMillis(value);
        }
        return calendar;
    }

    @TypeConverter
    public static  Long gregToTimeStamp(GregorianCalendar calendar){
        return calendar == null ? null : calendar.getTimeInMillis();
    }
}
