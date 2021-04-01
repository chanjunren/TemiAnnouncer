package com.robosolutions.temiannouncer.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.robosolutions.temiannouncer.db.TemiTaskDao;
import com.robosolutions.temiannouncer.db.TemiTaskRoomDatabase;
import com.robosolutions.temiannouncer.model.TemiTask;

import java.util.List;

// Meant to mediate between multiple data sources
// More complex example: https://github.com/android/architecture-components-samples/tree/main/BasicSample
public class TaskRepository {
    private TemiTaskDao mTemiTaskDao;
    private LiveData<List<TemiTask>> tasks;

    // Note that in order to unit test the Repository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    public TaskRepository(Application application) {
        TemiTaskRoomDatabase db = TemiTaskRoomDatabase.getDatabase(application);
        // Only need Dao to access DB, no need exposure to DB
        mTemiTaskDao = db.taskDao();
        tasks = mTemiTaskDao.getTasks();
    }

    public LiveData<List<TemiTask>> getAllTasks() {
        if (tasks.getValue() == null) {
            tasks = mTemiTaskDao.getTasks();
        }
        return tasks;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    public void insertTask(TemiTask temiTask) {
        TemiTaskRoomDatabase.getDbWriterExecutor().execute(() -> {
            System.out.println("inserting: " + temiTask);
            mTemiTaskDao.insert(temiTask);
        });
    }

}
