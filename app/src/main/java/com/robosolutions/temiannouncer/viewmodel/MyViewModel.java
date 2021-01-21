package com.robosolutions.temiannouncer.viewmodel;

import android.app.Application;
import android.os.Handler;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.robosolutions.temiannouncer.R;
import com.robosolutions.temiannouncer.model.Task;
import com.robosolutions.temiannouncer.repository.TaskRepository;

import java.util.ArrayList;
import java.util.List;

// Role of view Model is to survive configuration changes (like screen rotation) => Data not lost
// Provide data to UI
// Communication center between UI and repository
public class MyViewModel extends AndroidViewModel {
    private final TaskRepository mTaskRepo;
    private LiveData<List<Task>> taskLiveData;
    private ArrayList<Task> taskArrayList;

    public MyViewModel(Application application) {
        super(application);
        mTaskRepo = new TaskRepository(application);
        taskLiveData = new MutableLiveData<List<Task>>();
        init();
    }

    public LiveData<List<Task>> getTaskLiveData() {
        if (taskLiveData == null) {
            taskLiveData = mTaskRepo.getAllTasks();
        }
        return taskLiveData;
    }

    public void init() {
        populateTasks();
    }

    public void populateTasks() {
        // do async operation to fetch tasks
        ArrayList<Task> taskArrayList = (ArrayList<Task>) mTaskRepo.getAllTasks().getValue();
        System.out.println("Initial list: " + taskArrayList);
        Task task = new Task();
        task.setBackground(R.drawable.home_temi_logo);
        task.setTaskId("I'm a task!");
        task.setTaskIdx(1);
        insertTask(task);
        task.setTaskIdx(2);
        insertTask(task);
        task.setTaskIdx(3);
        insertTask(task);
    }

    // Created a wrapper insert() method that calls the Repository's insert() method.
    // In this way, the implementation of insert() is encapsulated from the UI.
    public void insertTask(Task task) {
        mTaskRepo.insertTask(task);
    }
}
