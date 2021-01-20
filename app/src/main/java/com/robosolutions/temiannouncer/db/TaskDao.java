package com.robosolutions.temiannouncer.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.robosolutions.temiannouncer.model.Task;

import java.util.List;

// Identifies this Interface as DAO class for Room
@Dao
public interface TaskDao {

    // https://developer.android.com/reference/androidx/room/OnConflictStrategy.html
    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insert(Task task);

    @Query("DELETE from taskTable")
    void deleteAll();

    // TO DO: Deleting only one entry

    @Query("SELECT * FROM taskTable ORDER BY taskIdx ASC")
    LiveData<List<Task>> getTasks();

}
