package com.robosolutions.temiannouncer.temi;

import com.robosolutions.temiannouncer.model.TemiTask;
import com.robotemi.sdk.Robot;

import java.util.ArrayList;

public class TemiController {
    private Robot robot;
    private static TemiController INSTANCE;

    private TemiController() {
        this.robot = Robot.getInstance();
    }

    public static TemiController getInstance() {
        if (INSTANCE != null) {
            return INSTANCE;
        }
        return new TemiController();
    }

    public ArrayList<String> getSavedLocations() {
//        return (ArrayList<String>) robot.getLocations();
        ArrayList<String> locations = new ArrayList<>();
        locations.add("location1");
        locations.add("location2");
        locations.add("location3");
        locations.add("location4");
        return (ArrayList<String>) locations;
    }

    public void executeSequence(TemiTask temiTask) {

    }
}
