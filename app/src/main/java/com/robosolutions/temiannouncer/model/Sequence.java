package com.robosolutions.temiannouncer.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

// Each entity class represents a SQLite table, you can specify the name of the table to be diff from
// the class
@Entity(tableName = "sequenceTable")
public class Sequence {
    // Annotations identify how each part of the class relates to an entry in the database
    @PrimaryKey
    @NonNull
    private int seqIdx;
    private String seqId;
    private List<Step> steps;
    private String imgPrevPath;
    private List<String> inputs;

    public Sequence() {
    }

    public int getSeqIdx() {
        return seqIdx;
    }

    public void setSeqIdx(int seqIdx) {
        this.seqIdx = seqIdx;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }

    public String getSeqId() {
        return seqId;
    }

    public void setSeqId(String seqId) {
        this.seqId = seqId;
    }

    public String getImgPrevPath() {
        return imgPrevPath;
    }

    public void setImgPrevPath(String imgPrevPath) {
        this.imgPrevPath = imgPrevPath;
    }

    public List<String> getInputs() {
        return inputs;
    }

    public void setInputs(List<String> inputs) {
        this.inputs = inputs;
    }

    @Override
    public String toString() {
        String stepsString = new String();
        for (int i = 0; i < steps.size(); i++) {
            stepsString += i + ": " + steps.get(i) + "\n";
        }

        return "Sequence{" +
                "===steps===\n" + stepsString +
                ", \nseqId='" + seqId + '\'' +
                ", imgPrevPath='" + imgPrevPath + '\'' +
                ", inputs=" + inputs +
                '}';
    }
}
