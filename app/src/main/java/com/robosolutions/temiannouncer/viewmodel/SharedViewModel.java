package com.robosolutions.temiannouncer.viewmodel;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.robosolutions.temiannouncer.google.DriveServiceHelper;
import com.robosolutions.temiannouncer.model.TemiStep;
import com.robosolutions.temiannouncer.model.TemiTask;
import com.robosolutions.temiannouncer.model.actions.TemiAction;
import com.robosolutions.temiannouncer.repository.TaskRepository;
import com.robosolutions.temiannouncer.utils.ActionEnum;

import java.util.Collections;
import java.util.List;

// Role of view Model is to survive configuration changes (like screen rotation) => Data not lost
// Provide data to UI
// Communication center between UI and repository
public class SharedViewModel extends AndroidViewModel {
    private final TaskRepository mTaskRepo;
    private LiveData<List<TemiTask>> taskLiveData;
    private DriveServiceHelper mDriveServiceHelper;

    // Variables for keeping track of task being created
    private TemiTask currentTask;
    private int currentStepIndex;
    private ActionEnum actionEnum;

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

    public void addMidActionToStep(TemiAction action, int stepIndex) {
//        this.currentTask.getTemiSteps().get(stepIndex).;
    }

    // Created a wrapper insert() method that calls the Repository's insert() method.
    // In this way, the implementation of insert() is encapsulated from the UI.
    public void insertTaskToDb(TemiTask temiTask) {
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

    public DriveServiceHelper getmDriveServiceHelper() {
        return mDriveServiceHelper;
    }

    public TemiTask getCurrentTask() {
        return currentTask;
    }

    public void setCurrentTask(TemiTask currentTask) {
        this.currentTask = currentTask;
    }

    public int getCurrentStepIndex() {
        return currentStepIndex;
    }

    public void setCurrentStepIndex(int currentStepIndex) {
        this.currentStepIndex = currentStepIndex;
    }

    public ActionEnum getActionEnum() {
        return actionEnum;
    }

    public void setActionEnum(ActionEnum actionEnum) {
        this.actionEnum = actionEnum;
    }

    public void initializeForTaskCreation() {
        this.currentTask = new TemiTask();
        currentTask.getTemiSteps().add(new TemiStep());
        actionEnum = null;
        currentStepIndex = -1;
    }

    public void saveCurrentAction(TemiAction action) {
        switch (actionEnum) {
            case LOCATION: {
                currentTask.getTemiSteps().get(currentStepIndex).setMovementAction(action);
                break;
            } case MID_ACTION: {
                currentTask.getTemiSteps().get(currentStepIndex).setMidAction(action);
                break;
            } case DEST_ACTION: {
                currentTask.getTemiSteps().get(currentStepIndex).setDestAction(action);
                break;
            }
        }
    }
}


