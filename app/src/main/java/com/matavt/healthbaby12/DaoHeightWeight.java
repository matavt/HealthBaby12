/*
Data Access Object(DAO) for interacting with HeightWeight Entities in Room DB.
This is an interface with SQL type queries forming part of the annotation.
Implementation is handled by RoomDB framework as part of the build.
 */
package com.matavt.healthbaby12;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

@Dao
public interface DaoHeightWeight {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertHeightWeight(EntityHeightWeight heightWeight);

    @Query("SELECT * from EntityHeightWeight")
    Flowable<List<EntityHeightWeight>> getAll();
}
