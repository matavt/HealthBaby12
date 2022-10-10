package com.matavt.healthbaby12;

import android.util.Log;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class DateFunctions {

    public static String createStringFromDate(int year, int month, int day) {
        String monthString;
        switch(month) {
            case 1:
                monthString = "Jan";
                break;
            case 2:
                monthString = "Feb";
                break;
            case 3:
                monthString = "Mar";
                break;
            case 4:
                monthString = "Apr";
                break;
            case 5:
                monthString = "May";
                break;
            case 6:
                monthString = "Jun";
                break;
            case 7:
                monthString = "Jul";
                break;
            case 8:
                monthString = "Aug";
                break;
            case 9:
                monthString = "Sep";
                break;
            case 10:
                monthString = "Oct";
                break;
            case 11:
                monthString = "Nov";
                break;
            case 12:
                monthString = "Dec";
                break;
            default:
                monthString = "err";
                break;
        }
        return monthString + " " + day + " " + year;
    }

    public static String getTodaysDate(){
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return createStringFromDate(year, month, day);
    }

    public static String createStringFromTime(int hour, int minute){
        String time;
        time = String.format("%02d:%02d",hour,minute);
        return time;
    }

    public static int calculateAgeInDays(GregorianCalendar birthday, GregorianCalendar date){
        long age = 0;
        long diff;
        diff = date.getTimeInMillis() - birthday.getTimeInMillis();
        age = (int)(diff/(1000*60*60*24));
        Log.i("age", birthday.getTimeInMillis() + ":" + date.getTimeInMillis()
                + " |calculateAgeInDays: " + diff +":" +age);
        Log.i("BirthDate", birthday.toString());
        Log.i("date", date.toString());
        return (int)age;
    }

    //this actually calculates in 4 weeks blocks but that is all we need for the chart.
    public static float calculateAgeInMoths(GregorianCalendar birthday, GregorianCalendar date){
        float age = 0;
        long diff;
        diff = date.getTimeInMillis() - birthday.getTimeInMillis();
        age = (int)(diff/(1000*60*60*24*28));
        Log.i("age", birthday.getTimeInMillis() + ":" + date.getTimeInMillis()
                + " |calculateAgeInDays: " + diff +":" +age);
        Log.i("BirthDate", birthday.toString());
        Log.i("date", date.toString());
        return age;
    }
}
