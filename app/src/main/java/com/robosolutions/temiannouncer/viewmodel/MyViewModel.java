package com.robosolutions.temiannouncer.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.robosolutions.temiannouncer.model.Task;
import com.robosolutions.temiannouncer.repository.TaskRepository;

import java.util.List;

// Role of view Model is to survive configuration changes (like screen rotation) => Data not lost
// provide data to UI
// Communication center between UI and repository
public class MyViewModel extends AndroidViewModel {
    private final TaskRepository mTaskRepo;

    // Why final?
    private final LiveData<List<Task>> mAllTasks;



    public MyViewModel(Application application) {
        super(application);
        mTaskRepo = new TaskRepository(application);
        mAllTasks = mTaskRepo.getAllTasks();
    }

    public LiveData<List<Task>> getAllTasks() {
        return mAllTasks;
    }
    // Created a wrapper insert() method that calls the Repository's insert() method.
    // In this way, the implementation of insert() is encapsulated from the UI.
    public void insertSequence(Task task) {
        mTaskRepo.insertTask(task);
    }
}
