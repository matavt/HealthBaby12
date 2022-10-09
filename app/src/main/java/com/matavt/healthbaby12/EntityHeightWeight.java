package com.matavt.healthbaby12;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Calendar;
import java.util.GregorianCalendar;

@Entity
public class EntityHeightWeight {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public float weight;
    public float height;
    public GregorianCalendar calendar;

    public EntityHeightWeight(float weight, float height) {
        this.weight = weight;
        this.height = height;
        this.calendar = new GregorianCalendar();
    }

    @NonNull
    @Override
    public String toString(){
        return "Date:" + DateFunctions.createStringFromDate(this.calendar.get(Calendar.YEAR),
                this.calendar.get(Calendar.MONTH), this.calendar.get(Calendar.DAY_OF_MONTH)) +
                "  Height:" + this.height +
                "  Weight:" + this.weight;
    }

}
