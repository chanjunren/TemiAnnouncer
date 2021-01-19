package com.robosolutions.temiannouncer.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.robosolutions.temiannouncer.model.Sequence;
import com.robosolutions.temiannouncer.repository.SequenceRepository;

import java.util.List;

// Role of view Model is to survive configuration changes (like screen rotation) => Data not lost
// provide data to UI
// Communication center between UI and repository
public class SavedViewModel extends AndroidViewModel {
    private final SequenceRepository repository;

    private final LiveData<List<Sequence>> allSequences;

    public SavedViewModel(Application application) {
        super(application);
        repository = new SequenceRepository(application);
        allSequences = repository.getAllSequences();
    }

    LiveData<List<Sequence>> getAllSequences() {
        return allSequences;
    }
    // Created a wrapper insert() method that calls the Repository's insert() method.
    // In this way, the implementation of insert() is encapsulated from the UI.
    public void insertSequence(Sequence sequence) {
        repository.insertSequence(sequence);
    }
}
