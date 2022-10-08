package com.matavt.healthbaby12;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

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
}
