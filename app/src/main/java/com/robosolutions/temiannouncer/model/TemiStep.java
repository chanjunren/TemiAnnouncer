package com.robosolutions.temiannouncer.model;

import com.robosolutions.temiannouncer.model.actions.TemiAction;

import java.util.Optional;

public class TemiStep {
    private TemiAction midAction;
    private TemiAction destAction;
    private TemiAction movementAction;

    public TemiStep() {
    }

    public TemiAction getMidAction() {
        return midAction;
    }

    public void setMidAction(TemiAction midAction) {
        this.midAction = midAction;
    }

    public TemiAction getDestAction() {
        return destAction;
    }

    public void setDestAction(TemiAction destAction) {
        this.destAction = destAction;
    }

    public TemiAction getMovementAction() {
        return movementAction;
    }

    public void setMovementAction(TemiAction movementAction) {
        this.movementAction = movementAction;
    }
}
