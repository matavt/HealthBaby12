//Configure the Room DB.
package com.matavt.healthbaby12;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

//Specifies the Entity classes that from the bases of the Room BD tables
@Database(entities = {EntityHeightWeight.class, EntityActivity.class,
        EntityMilestone.class, EntityChild.class}
        , exportSchema = false, version = 22)
//We use a type converter to allow GregorianCalendar Object to be stored in the Room DB tables.
@TypeConverters({Converters.class})
public abstract class HealthBabyDB extends RoomDatabase {
    private static final String DB_NAME = "health_baby_db";
    private static volatile HealthBabyDB instance;

    //Implemented as a singleton object
    public static synchronized HealthBabyDB getInstance(Context context) {
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), HealthBabyDB.class, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
    //The interfaces that are our Data Access Objects.
    public abstract DaoHeightWeight daoHeightWeight();
    public abstract DaoActivity daoActivity();
    public abstract DaoMilestone daoMilestone();
    public abstract DaoChild daoChild();
}