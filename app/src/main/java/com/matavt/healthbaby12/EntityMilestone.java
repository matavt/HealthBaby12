package com.matavt.healthbaby12;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class EntityMilestone {
    @PrimaryKey
    public int id;

    public boolean achieved;
    public String date;

    public EntityMilestone(int id, boolean achieved, String date) {
        this.id = id;
        this.achieved = achieved;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public boolean getAchieved() {
        return achieved;
    }
}
