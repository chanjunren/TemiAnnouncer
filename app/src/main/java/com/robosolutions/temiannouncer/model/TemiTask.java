package com.robosolutions.temiannouncer.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.robosolutions.temiannouncer.utils.TypeConverter;

import java.util.ArrayList;

// Each entity class represents a SQLite table, you can specify the name of the table to be diff from
// the class
@Entity(tableName = "taskTable")
public class TemiTask {
    // Annotations identify how each part of the class relates to an entry in the database
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int taskIdx;
    @ColumnInfo (name="taskId")
    private String taskId;

    @TypeConverters(TypeConverter.class)
    private ArrayList<TemiStep> temiSteps;
    private String imgPrevPath;
    private int background;

    @TypeConverters(TypeConverter.class)
    private ArrayList<String> inputs;

    public TemiTask() {
        temiSteps = new ArrayList<>();

    }

    public TemiTask(String taskId, int background) {
        this.taskId = taskId;
        this.background = background;
        temiSteps = new ArrayList<>();
    }

    public int getTaskIdx() {
        return taskIdx;
    }

    public void setTaskIdx(int taskIdx) {
        this.taskIdx = taskIdx;
    }

    public ArrayList<TemiStep> getTemiSteps() {
        return temiSteps;
    }

    public void setTemiSteps(ArrayList<TemiStep> temiSteps) {
        this.temiSteps = temiSteps;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getImgPrevPath() {
        return imgPrevPath;
    }

    public void setImgPrevPath(String imgPrevPath) {
        this.imgPrevPath = imgPrevPath;
    }

    public ArrayList<String> getInputs() {
        return inputs;
    }

    public void setInputs(ArrayList<String> inputs) {
        this.inputs = inputs;
    }

    public int getBackground() {
        return background;
    }

    public void setBackground(int background) {
        this.background = background;
    }

    @Override
    public String toString() {
        String taskString = new String();
        for (int i = 0; i < temiSteps.size(); i++) {
            taskString += i + ": " + temiSteps.get(i) + "\n";
        }

        return "TemiTask {\n" +
                "\t===temiSteps===\n" + taskString +
                ", \n\ttaskIdx: " + taskIdx +
                ", \n\ttaskId: '" + taskId + '\'' +
                ", \n\timgPrevPath: '" + imgPrevPath + '\'' +
                ", \n\tinputs: " + inputs +
                "\n}";
    }
}
