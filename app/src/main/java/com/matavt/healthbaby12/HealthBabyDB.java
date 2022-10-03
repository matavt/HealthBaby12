package com.matavt.healthbaby12;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {EntityHeightWeight.class, EntityActivity.class,
        EntityMilestone.class, EntityChild.class}
        , exportSchema = false, version = 14)
@TypeConverters({Converters.class})
public abstract class HealthBabyDB extends RoomDatabase {
    private static final String DB_NAME = "health_baby_db";
    private static volatile HealthBabyDB instance;

    public static synchronized HealthBabyDB getInstance(Context context) {
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), HealthBabyDB.class, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
    public abstract DaoHeightWeight daoHeightWeight();
    public abstract DaoActivity daoActivity();
    public abstract DaoMilestone daoMilestone();
    public abstract DaoChild daoChild();
}
