package com.matavt.healthbaby12;

import androidx.room.TypeConverter;

import java.util.GregorianCalendar;

public class Converters {

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
