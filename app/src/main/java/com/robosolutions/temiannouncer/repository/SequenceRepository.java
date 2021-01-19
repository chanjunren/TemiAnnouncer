package com.robosolutions.temiannouncer.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.robosolutions.temiannouncer.db.SequenceDao;
import com.robosolutions.temiannouncer.db.SequenceRoomDatabase;
import com.robosolutions.temiannouncer.model.Sequence;

import java.util.List;

// Meant to mediate between multiple data sources
// More complex example: https://github.com/android/architecture-components-samples/tree/main/BasicSample
public class SequenceRepository {
    private SequenceDao seqDao;
    private LiveData<List<Sequence>> seqs;

    // Note that in order to unit test the Repository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    public SequenceRepository(Application application) {
        SequenceRoomDatabase db = SequenceRoomDatabase.getDatabase(application);
        // Only need Dao to access DB, no need exposure to DB
        seqDao = db.sequenceDao();
        seqs = seqDao.getSequences();
    }

    public LiveData<List<Sequence>> getAllSequences() {
        return seqs;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    public void insertSequence(Sequence sequence) {
        SequenceRoomDatabase.getDbWriterExecutor().execute(() -> {
            seqDao.insert(sequence);
        });
    }

}
