package com.matavt.healthbaby12;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class EntityHeightWeight {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public float weight;
    public float height;
    public Date date;

    public EntityHeightWeight(float weight, float height) {
        this.weight = weight;
        this.height = height;
        this.date = new Date(System.currentTimeMillis());
    }

    @NonNull
    @Override
    public String toString(){
        return "Date:" + this.date +
                "  Height:" + this.height +
                "  Weight:" + this.weight;
    }

}
