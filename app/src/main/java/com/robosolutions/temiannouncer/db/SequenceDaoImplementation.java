package com.robosolutions.temiannouncer.db;

import androidx.lifecycle.MutableLiveData;

import com.robosolutions.temiannouncer.model.Sequence;

import java.util.ArrayList;
import java.util.List;

// Interface defining all allowed actions to manipulate data in a DB
// Validates SQL at compile time and associates it with a method
public class SequenceDaoImplementation {
    private List<Sequence> sequences;
    private MutableLiveData<List<Sequence>> liveData;

    public SequenceDaoImplementation() {
        this.liveData = new MutableLiveData<List<Sequence>>();
        sequences = new ArrayList<>();
    }

    public void addSequence(Sequence sequence) {
        this.sequences.add(sequence);
        liveData.postValue(sequences);
    }

    public void removeSequence(int idx) {
        this.sequences.remove(idx);
        liveData.postValue(sequences);
    }
}
