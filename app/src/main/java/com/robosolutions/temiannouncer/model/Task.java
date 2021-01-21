package com.robosolutions.temiannouncer.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.robosolutions.temiannouncer.utils.TypeConverter;

import java.util.ArrayList;
import java.util.List;

// Each entity class represents a SQLite table, you can specify the name of the table to be diff from
// the class
@Entity(tableName = "taskTable")
public class Task {
    // Annotations identify how each part of the class relates to an entry in the database
    @PrimaryKey
    @NonNull
    private int taskIdx;
    @ColumnInfo (name="taskId")
    private String taskId;

    @TypeConverters(TypeConverter.class)
    private List<Step> steps;
    private String imgPrevPath;
    private int background;

    @TypeConverters(TypeConverter.class)
    private List<String> inputs;

    public Task() {
        steps = new ArrayList<>();

    }

    public Task(String taskId, int background) {
        this.taskId = taskId;
        this.background = background;
        steps = new ArrayList<>();
    }

    public int getTaskIdx() {
        return taskIdx;
    }

    public void setTaskIdx(int taskIdx) {
        this.taskIdx = taskIdx;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
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

    public List<String> getInputs() {
        return inputs;
    }

    public void setInputs(List<String> inputs) {
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
        for (int i = 0; i < steps.size(); i++) {
            taskString += i + ": " + steps.get(i) + "\n";
        }

        return "Task {\n" +
                "\t===steps===\n" + taskString +
                ", \n\ttaskIdx: " + taskIdx +
                ", \n\ttaskId: '" + taskId + '\'' +
                ", \n\timgPrevPath: '" + imgPrevPath + '\'' +
                ", \n\tinputs: " + inputs +
                "\n}";
    }
}
