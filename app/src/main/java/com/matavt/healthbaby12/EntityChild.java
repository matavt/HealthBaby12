package com.matavt.healthbaby12;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.GregorianCalendar;

@Entity
public class EntityChild {
    @PrimaryKey
    public int id;

    public String name;
    public GregorianCalendar date;

    public EntityChild(String name, GregorianCalendar date) {
        this.id = 1;
        this.name = name;
        this.date = date;
    }

    public GregorianCalendar getDate() {
        return date;
    }

    public String getName() {
        return name;
    }
}
