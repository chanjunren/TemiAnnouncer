package com.robosolutions.temiannouncer.db;

// To pull all of the Database objects in one place
public class FakeDatabase {
    private static FakeDatabase singleInstance = null;
    private SequenceDao sequenceDao;

    private FakeDatabase() {
        this.sequenceDao = new SequenceDao();
    }

    public static FakeDatabase getInstance() {
        if (singleInstance == null) {
            singleInstance = new FakeDatabase();
        }
        return singleInstance;
    }
}
