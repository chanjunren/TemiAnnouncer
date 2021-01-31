package com.robosolutions.temiannouncer.model.actions;

import java.util.HashMap;

public class SpeechAction implements TemiAction {
    private String text;
//    private HashMap<String, String> inputMap;


    public SpeechAction(String text) {
        this.text = text;
    }

    @Override
    public void executeAction() {

    }
}
