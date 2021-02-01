package com.robosolutions.temiannouncer.model.actions;

import android.graphics.Bitmap;

public class ImageAction implements TemiAction {
    private String imgPath;
    private Bitmap imgThumbnail;
    private int duration;
    private String imgTitle;

    public ImageAction(String imgPath, Bitmap imgThumbnail, int duration, String imgTitle) {
        this.imgPath = imgPath;
        this.imgThumbnail = imgThumbnail;
        this.duration = duration;
        this.imgTitle = imgTitle;
    }

    @Override
    public void executeAction() {

    }



    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public Bitmap getImgThumbnail() {
        return imgThumbnail;
    }

    public void setImgThumbnail(Bitmap imgThumbnail) {
        this.imgThumbnail = imgThumbnail;
    }
    @Override
    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getImgTitle() {
        return imgTitle;
    }

    public void setImgTitle(String imgTitle) {
        this.imgTitle = imgTitle;
    }
}
