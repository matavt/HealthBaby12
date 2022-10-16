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
public class EntityMilestone {
    @PrimaryKey
    public int id;

    public boolean achieved;
    public GregorianCalendar calendar;
    public String description;

    public EntityMilestone(int id, boolean achieved, GregorianCalendar calendar, String description) {
        this.id = id;
        this.achieved = achieved;
        this.calendar = calendar;
        this.description = description;
    }

    public int getId() {
        return this.id;
    }

    public GregorianCalendar getDate() {
        return this.calendar;
    }

    public boolean getAchieved() {
        return this.achieved;
    }

    @NonNull
    @Override
    public String toString() {
        String dateFormatted = DateFunctions.createStringFromDate(this.calendar.get(Calendar.YEAR),
                this.calendar.get(Calendar.MONTH) + 1, this.calendar.get(Calendar.DAY_OF_MONTH));
        return description +
                " achieved on " +
                dateFormatted;
    }
}
