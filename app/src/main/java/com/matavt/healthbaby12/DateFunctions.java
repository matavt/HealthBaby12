package com.matavt.healthbaby12;

import java.util.Calendar;

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
        return createStringFromDate(day, month, year);
    }

    public static String createStringFromTime(int hour, int minute){
        return hour + ":" + minute;
    }
}
