package com.robosolutions.temiannouncer.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.robosolutions.temiannouncer.model.TemiTask;
import com.robosolutions.temiannouncer.repository.TaskRepository;

import java.util.List;

// Role of view Model is to survive configuration changes (like screen rotation) => Data not lost
// Provide data to UI
// Communication center between UI and repository
public class SharedViewModel extends AndroidViewModel {
    private final TaskRepository mTaskRepo;
    private LiveData<List<TemiTask>> taskLiveData;
    private GoogleSignInAccount googleSignInAccount;

    public SharedViewModel(Application application) {
        super(application);
        mTaskRepo = new TaskRepository(application);
        taskLiveData = mTaskRepo.getAllTasks();
    }

    public LiveData<List<TemiTask>> getTaskLiveData() {
        if (taskLiveData == null) {
            taskLiveData = mTaskRepo.getAllTasks();
        }
        return taskLiveData;
    }

    // Created a wrapper insert() method that calls the Repository's insert() method.
    // In this way, the implementation of insert() is encapsulated from the UI.
    public void insertTask(TemiTask temiTask) {
        mTaskRepo.insertTask(temiTask);
    }

    public GoogleSignInAccount getGoogleSignInAccount() {
        return googleSignInAccount;
    }

    public void setGoogleSignInAccount(GoogleSignInAccount googleSignInAccount) {
        this.googleSignInAccount = googleSignInAccount;
    }
}
