package com.robosolutions.temiannouncer.viewmodel;

import android.accounts.Account;
import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.robosolutions.temiannouncer.google.DriveServiceHelper;
import com.robosolutions.temiannouncer.model.TemiTask;
import com.robosolutions.temiannouncer.repository.TaskRepository;

import java.util.Collections;
import java.util.List;

// Role of view Model is to survive configuration changes (like screen rotation) => Data not lost
// Provide data to UI
// Communication center between UI and repository
public class SharedViewModel extends AndroidViewModel {
    private final TaskRepository mTaskRepo;
    private LiveData<List<TemiTask>> taskLiveData;
    private DriveServiceHelper mDriveServiceHelper;

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

    public void initializeGoogleServices(GoogleSignInAccount googleSignInAccount, Context context) {
        GoogleAccountCredential credential =
                GoogleAccountCredential.usingOAuth2(
                        context, Collections.singleton(DriveScopes.DRIVE_FILE));

        credential.setSelectedAccount(googleSignInAccount.getAccount());
        Drive googleDriveService = new Drive.Builder(
                AndroidHttp.newCompatibleTransport(),
                new GsonFactory(),
                credential)
                .build();
        mDriveServiceHelper = new DriveServiceHelper(googleDriveService);
    }
}
