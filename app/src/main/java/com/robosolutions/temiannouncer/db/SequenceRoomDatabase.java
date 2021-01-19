package com.robosolutions.temiannouncer.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.robosolutions.temiannouncer.model.Sequence;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//Database migrations are beyond the scope of this codelab,
//so we set exportSchema to false here to avoid a build warning.
//In a real app, you should consider setting a directory for Room to use to export
//the schema so you can check the current schema into your version control system.

// https://medium.com/google-developers/understanding-migrations-with-room-f01e04b07929
@Database(entities = {Sequence.class}, version = 1, exportSchema = false)
public abstract class SequenceRoomDatabase extends RoomDatabase {
    public abstract SequenceDao sequenceDao();

    private static volatile SequenceRoomDatabase INSTANCE;

    private static final int THREAD_COUNT = 4;
    // For running DB operations asynchronously
    static final ExecutorService dbWriterExecutor = Executors.newFixedThreadPool(THREAD_COUNT);

    public static SequenceRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                SequenceRoomDatabase.class, "sequenceDatabase").build();
        }
        return INSTANCE;
    }

    public static ExecutorService getDbWriterExecutor() {
        return dbWriterExecutor;
    }
}