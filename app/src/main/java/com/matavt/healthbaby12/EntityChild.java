package com.matavt.healthbaby12;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class EntityChild {
    @PrimaryKey
    public int id;

    public String name;
    public Date date;

    public EntityChild(String name, Date date) {
        this.id = 1;
        this.name = name;
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public String getName() {
        return name;
    }
}
