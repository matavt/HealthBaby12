/*
Data Access Object(DAO) for interacting with Activity Entities in Room DB.
This is an interface with SQL type queries forming part of the annotation.
Implementation is handled by RoomDB framework as part of the build.
 */

package com.matavt.healthbaby12;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Completable;

@Dao
public interface DaoActivity {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertActivity(EntityActivity activity);

    @Query("SELECT * from EntityActivity")
    Flowable<List<EntityActivity>> getAll();

    @Query("DELETE from EntityActivity")
    Completable deleteAllActivity();
}