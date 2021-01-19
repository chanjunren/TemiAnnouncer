package com.robosolutions.temiannouncer.model;

import java.util.List;

public class Sequence {
    private List<Step> steps;
    private String seqId;
    private String imgPrevPath;
    private List<String> inputs;

    public Sequence() {
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
