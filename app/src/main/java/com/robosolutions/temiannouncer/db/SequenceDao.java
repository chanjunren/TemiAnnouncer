package com.robosolutions.temiannouncer.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.robosolutions.temiannouncer.model.Sequence;

import java.util.List;

// Identifies this Interface as DAO class for Room
@Dao
public interface SequenceDao {

    // https://developer.android.com/reference/androidx/room/OnConflictStrategy.html
    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insert(Sequence sequence);

    @Query("DELETE from sequenceTable")
    void deleteAll();

    // TO DO: Deleting only one entry

    @Query("SELECT * FROM sequenceTable ORDER BY seqIdx ASC")
    LiveData<List<Sequence>> getSequences();

}
