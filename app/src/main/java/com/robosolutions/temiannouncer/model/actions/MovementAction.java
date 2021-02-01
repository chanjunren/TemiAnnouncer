package com.robosolutions.temiannouncer.model.actions;

public class MovementAction implements TemiAction {
    private String destination;

    public MovementAction(String destination) {
        this.destination = destination;
    }

    @Override
    public void executeAction() {

    }

    @Override
    public int getDuration() {
        return 0;
    }
}
