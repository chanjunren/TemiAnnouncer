package com.robosolutions.temiannouncer.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.robosolutions.temiannouncer.db.TaskDao;
import com.robosolutions.temiannouncer.db.TaskRoomDatabase;
import com.robosolutions.temiannouncer.model.Task;

import java.util.List;

// Meant to mediate between multiple data sources
// More complex example: https://github.com/android/architecture-components-samples/tree/main/BasicSample
public class TaskRepository {
    private TaskDao mTaskDao;
    private LiveData<List<Task>> tasks;

    // Note that in order to unit test the Repository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    public TaskRepository(Application application) {
        TaskRoomDatabase db = TaskRoomDatabase.getDatabase(application);
        // Only need Dao to access DB, no need exposure to DB
        mTaskDao = db.taskDao();
        tasks = mTaskDao.getTasks();
    }

    public LiveData<List<Task>> getAllTasks() {
        if (tasks.getValue() == null) {
            tasks = mTaskDao.getTasks();
        }
        return tasks;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    public void insertTask(Task task) {
        TaskRoomDatabase.getDbWriterExecutor().execute(() -> {
            System.out.println("inserting: " + task);
            mTaskDao.insert(task);
        });
    }

}