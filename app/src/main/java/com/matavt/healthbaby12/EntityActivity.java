package com.matavt.healthbaby12;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class EntityActivity {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String record;

    public EntityActivity(String record) {
        this.record = record;
    }

    @NonNull
    @Override
    public String toString() {
        return id + ": " + this.record;
    }
}
