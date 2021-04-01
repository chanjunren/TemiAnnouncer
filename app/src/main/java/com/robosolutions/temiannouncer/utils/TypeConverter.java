package com.robosolutions.temiannouncer.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.robosolutions.temiannouncer.model.TemiStep;

import java.lang.reflect.Type;
import java.util.ArrayList;

// Used for converting List of objects into suitable format to store into the Database
public class TypeConverter {
    @androidx.room.TypeConverter
    public static ArrayList<TemiStep> stringToStepList(String data) {
        Gson gson = new Gson();
        if (data == null) {
            return new ArrayList<>();
        }
        Type listType = new TypeToken<ArrayList<TemiStep>>() {}.getType();
        return gson.fromJson(data, listType);
    }

    @androidx.room.TypeConverter
    public static String stepListToString(ArrayList<TemiStep> temiSteps) {
        Gson gson = new Gson();
        return gson.toJson(temiSteps);
    }

    @androidx.room.TypeConverter
    public static ArrayList<String> stringToInputList(String data) {
        Gson gson = new Gson();
        if (data == null) {
            return new ArrayList<>();
        }

        Type listType = new TypeToken<ArrayList<String>>() {}.getType();
        return gson.fromJson(data, listType);
    }

    @androidx.room.TypeConverter
    public static String inputListToString(ArrayList<String> inputs) {
        Gson gson = new Gson();
        return gson.toJson(inputs);
    }
}
