package com.robosolutions.temiannouncer.model.actions;

import android.graphics.Bitmap;

public class VideoAction implements TemiAction {
    private String videoPath;
    private String videoTitle;
    private Bitmap videoThumbnail;

    public VideoAction(String videoPath, String videoTitle, Bitmap videoThumbnail) {
        this.videoPath = videoPath;
        this.videoTitle = videoTitle;
        this.videoThumbnail = videoThumbnail;
    }

    @Override
    public void executeAction() {

    }

    @Override
    public int getDuration() {
        return 0;
    }

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public Bitmap getVideoThumbnail() {
        return videoThumbnail;
    }

    public void setVideoThumbnail(Bitmap videoThumbnail) {
        this.videoThumbnail = videoThumbnail;
    }
}
