/*
The storage entity for Activities for the Room Db. @Entity annotation flags the member variables
as values to be stored in this entities table.
toString overwritten to be used for information display
 */

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
