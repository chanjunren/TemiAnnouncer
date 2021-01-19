package com.robosolutions.temiannouncer.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.robosolutions.temiannouncer.model.Step;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

// Used for converting List of objects into suitable format to store into the Database
public class TypeConverter {
    @androidx.room.TypeConverter
    public static List<Step> stringToStepList(String data) {
        Gson gson = new Gson();
        if (data == null) {
            return Collections.emptyList();
        }
        Type listType = new TypeToken<Step>() {}.getType();
        return gson.fromJson(data, listType);
    }

    @androidx.room.TypeConverter
    public static String stepListToString(List<Step> steps) {
        Gson gson = new Gson();
        return gson.toJson(steps);
    }

    @androidx.room.TypeConverter
    public static List<String> stringToInputList(String data) {
        Gson gson = new Gson();
        if (data == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<String>() {}.getType();
        return gson.fromJson(data, listType);
    }

    @androidx.room.TypeConverter
    public static String inputListToString(List<String> inputs) {
        Gson gson = new Gson();
        return gson.toJson(inputs);
    }
}
