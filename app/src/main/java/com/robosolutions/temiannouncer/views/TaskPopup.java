package com.robosolutions.temiannouncer.views;

import android.app.Dialog;
import android.widget.Button;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.services.drive.DriveScopes;
import com.robosolutions.temiannouncer.R;
import com.robosolutions.temiannouncer.google.DriveServiceHelper;
import com.robosolutions.temiannouncer.google.MediaRetriever;

import java.util.Collections;

public class TaskPopup {
    private Fragment parent;
    private Dialog dialog;
    private DriveServiceHelper mDriveServiceHelper;

    public TaskPopup(Fragment frag) {
        this.parent = frag;
        this.dialog = new Dialog(parent.getContext());
        GoogleAccountCredential credential =
                GoogleAccountCredential.usingOAuth2(
                        parent.getContext(), Collections.singleton(DriveScopes.DRIVE_FILE));
//        credential.setSelectedAccount()
//        mDriveServiceHelper =
    }

    public void showPromptPage() {
        dialog.setContentView(R.layout.popup_task);
        ImageView imgPopupBtn = dialog.findViewById(R.id.showImgPopupBtn);
        ImageView videoPopupBtn = dialog.findViewById(R.id.showVideoPopupBtn);
        ImageView speechPopupBtn = dialog.findViewById(R.id.showSpeechPopupBtn);
        ImageView exitPrompt = dialog.findViewById(R.id.exitPopupTaskBtn);
        imgPopupBtn.setOnClickListener(v -> {
            showImgPage();
        });
        videoPopupBtn.setOnClickListener(v -> {
            showVideoPage();
        });
        speechPopupBtn.setOnClickListener(v -> {
            showSpeechPage();
        });
        exitPrompt.setOnClickListener(v -> {
            dialog.dismiss();
        });
        dialog.show();
    }
    private void showImgPage() {
        dialog.setContentView(R.layout.popup_img);
        ImageView exitPrompt = dialog.findViewById(R.id.exitPopupTaskBtn);
        exitPrompt.setOnClickListener(v -> {
            dialog.dismiss();
        });

        Button selectImgBtn = dialog.findViewById(R.id.selectImgBtn);
        selectImgBtn.setOnClickListener(v -> {
            mDriveServiceHelper.queryFiles();
        });
    }

    private void showVideoPage() {
        dialog.setContentView(R.layout.popup_video);
        ImageView exitPrompt = dialog.findViewById(R.id.exitPopupTaskBtn);
        exitPrompt.setOnClickListener(v -> {
            dialog.dismiss();
        });

        Button selectImgBtn = dialog.findViewById(R.id.selectVidBtn);

    }

    private void showSpeechPage() {
        dialog.setContentView(R.layout.popup_speech);
        ImageView exitPrompt = dialog.findViewById(R.id.exitPopupTaskBtn);
        exitPrompt.setOnClickListener(v -> {
            dialog.dismiss();
        });
    }


}
