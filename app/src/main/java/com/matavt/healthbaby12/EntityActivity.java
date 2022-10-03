package com.matavt.healthbaby12;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class EntityActivity {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String record;

    public EntityActivity(String record) {
        this.record = record;
    }

    @Override
    public String toString() {
        return id + ": " + this.record;
    }
}
