/*
Type converters for the Room db instance.
These functions are used by  Room DB on reading and written GregorianCalendar objects to the RoomDB
GregorianCalendars object are stored as Long values in the DB.
 */

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
        Long value;
        if (calendar == null){
            value = null;
        } else {
            value = calendar.getTimeInMillis();
        }
        return value;
    }
}
