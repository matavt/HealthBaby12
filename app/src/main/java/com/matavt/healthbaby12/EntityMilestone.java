package com.matavt.healthbaby12;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class EntityMilestone {
    @PrimaryKey
    public int id;

    public boolean achieved;
    public Date date;
    public String description;

    public EntityMilestone(int id, boolean achieved, Date date, String description) {
        this.id = id;
        this.achieved = achieved;
        this.date = date;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public boolean getAchieved() {
        return achieved;
    }

    @Override
    public String toString() {
        String dateFormatted = DateFunctions.createStringFromDate(this.date.getYear()+1900,
                this.date.getMonth() + 1, this.date.getDay());
        return description +
                " achieved on " +
                dateFormatted;
    }
}
