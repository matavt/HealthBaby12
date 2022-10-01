package com.matavt.healthbaby12;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.time.LocalDate;
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

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Date:").append(this.date);
        sb.append("  Height:").append(this.height);
        sb.append("  Weight:").append(this.weight);
        return sb.toString();
    }

}
