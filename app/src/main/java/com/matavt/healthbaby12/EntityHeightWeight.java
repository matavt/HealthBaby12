/*
The storage entity for Activities for the Room Db. @Entity annotation flags the member variables
as values to be stored in this entities table.
toString overwritten to be used for information display
 */

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
        GregorianCalendar cal = new GregorianCalendar();
        this.weight = weight;
        this.height = height;
        this.calendar = cal;
    }

    @NonNull
    @Override
    public String toString(){
        return "Date:" + DateFunctions.createStringFromDate(this.calendar.get(Calendar.YEAR),
                this.calendar.get(Calendar.MONTH)+1, this.calendar.get(Calendar.DAY_OF_MONTH)) +
                "  Height:" + this.height +
                "  Weight:" + this.weight;
    }
}
