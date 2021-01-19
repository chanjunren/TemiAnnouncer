package com.robosolutions.temiannouncer.model;

import android.os.UserManager;

import org.w3c.dom.ls.LSOutput;

import java.util.HashMap;

public class Step {
    private String imagePath;
    private int imageDur;
    private String audioPath;
    private int audioDur;
    private String videoPath;
    private int videoDur;
    private String speechTxt;
    private int speechInterval;
    private int speechCount;
    // <inputTitle, inputValue>
    private HashMap<String, String> speechInputs;

    public Step() {
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getImageDur() {
        return imageDur;
    }

    public void setImageDur(int imageDur) {
        this.imageDur = imageDur;
    }

    public String getAudioPath() {
        return audioPath;
    }

    public void setAudioPath(String audioPath) {
        this.audioPath = audioPath;
    }

    public int getAudioDur() {
        return audioDur;
    }

    public void setAudioDur(int audioDur) {
        this.audioDur = audioDur;
    }

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    public int getVideoDur() {
        return videoDur;
    }

    public void setVideoDur(int videoDur) {
        this.videoDur = videoDur;
    }

    public String getSpeechTxt() {
        return speechTxt;
    }

    public void setSpeechTxt(String speechTxt) {
        this.speechTxt = speechTxt;
    }

    public int getSpeechInterval() {
        return speechInterval;
    }

    public void setSpeechInterval(int speechInterval) {
        this.speechInterval = speechInterval;
    }

    public int getSpeechCount() {
        return speechCount;
    }

    public void setSpeechCount(int speechCount) {
        this.speechCount = speechCount;
    }

    @Override
    public String toString() {
        return "Step{" +
                "imagePath='" + imagePath + '\'' +
                ", imageDur=" + imageDur +
                ", audioPath='" + audioPath + '\'' +
                ", audioDur=" + audioDur +
                ", videoPath='" + videoPath + '\'' +
                ", videoDur=" + videoDur +
                ", speechTxt='" + speechTxt + '\'' +
                ", speechInterval=" + speechInterval +
                ", speechCount=" + speechCount +
                ", speechInputs=" + speechInputs +
                '}';
    }
}
