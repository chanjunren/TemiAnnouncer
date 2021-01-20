package com.robosolutions.temiannouncer.db;

import androidx.lifecycle.MutableLiveData;

import com.robosolutions.temiannouncer.model.Task;

import java.util.ArrayList;
import java.util.List;

// Interface defining all allowed actions to manipulate data in a DB
// Validates SQL at compile time and associates it with a method
public class TaskDaoImplementation {
    private List<Task> tasks;
    private MutableLiveData<List<Task>> liveData;

    public TaskDaoImplementation() {
        this.liveData = new MutableLiveData<List<Task>>();
        tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        this.tasks.add(task);
        liveData.postValue(tasks);
    }

    public void removeTask(int idx) {
        this.tasks.remove(idx);
        liveData.postValue(tasks);
    }
}
