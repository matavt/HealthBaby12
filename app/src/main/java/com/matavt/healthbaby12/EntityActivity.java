package com.matavt.healthbaby12;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class EntityActivity {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String dateTime;
    public String activityType;
    public String duration;
    public float value;
    public String description;

    public EntityActivity(String dateTime, String activityType, String duration, float value, String description) {
        this.dateTime = dateTime;
        this.activityType = activityType;
        this.duration = duration;
        this.value = value;
        this.description = description;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(dateTime).append(": ").append(activityType).append(" for").append(duration)
                .append("with ").append(value).append(description);
        return sb.toString();
    }
}
