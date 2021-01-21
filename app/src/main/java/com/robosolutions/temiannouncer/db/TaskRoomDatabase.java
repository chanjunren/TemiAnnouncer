package com.robosolutions.temiannouncer.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.robosolutions.temiannouncer.model.Task;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//Database migrations are beyond the scope of this codelab,
//so we set exportSchema to false here to avoid a build warning.
//In a real app, you should consider setting a directory for Room to use to export
//the schema so you can check the current schema into your version control system.

// https://medium.com/google-developers/understanding-migrations-with-room-f01e04b07929
@Database(entities = {Task.class}, version = 1, exportSchema = false)
public abstract class TaskRoomDatabase extends RoomDatabase {
    public abstract TaskDao taskDao();

    private static volatile TaskRoomDatabase INSTANCE;

    private static final int THREAD_COUNT = 4;
    // For running DB operations asynchronously
    static final ExecutorService dbWriterExecutor = Executors.newFixedThreadPool(THREAD_COUNT);

    public static TaskRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            // Can append addCallback(sRoomDatabaseCallback) for future development if needed
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                TaskRoomDatabase.class, "taskDatabase").build();
        }
        return INSTANCE;
    }

    public static ExecutorService getDbWriterExecutor() {
        return dbWriterExecutor;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

//            dbWriterExecutor.execute(() -> {
//                TaskDao dao = INSTANCE.sequenceDao();
//                dao.deleteAll();
//
//                // Can add some default words
//                Word word = new Word("Hello");
//                dao.insert(word);
//                word = new Word("World");
//                dao.insert(word);
//            });
        }
    };
}
