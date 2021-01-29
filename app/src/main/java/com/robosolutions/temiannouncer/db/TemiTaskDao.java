package com.robosolutions.temiannouncer.db;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.robosolutions.temiannouncer.model.TemiTask;

import java.util.List;

// Identifies this Interface as DAO class for Room
@Dao
public interface TemiTaskDao {

    // https://developer.android.com/reference/androidx/room/OnConflictStrategy.html
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(TemiTask temiTask);

    @Query("DELETE from taskTable")
    void deleteAll();

    // TO DO: Deleting only one entry

    @Query("SELECT * FROM taskTable ORDER BY taskIdx ASC")
    LiveData<List<TemiTask>> getTasks();

}
