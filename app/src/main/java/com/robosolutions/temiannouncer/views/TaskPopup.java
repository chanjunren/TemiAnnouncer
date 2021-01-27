package com.robosolutions.temiannouncer.views;

import android.app.Dialog;
import android.widget.Button;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.robosolutions.temiannouncer.R;
import com.robosolutions.temiannouncer.google.DriveServiceHelper;
import com.robosolutions.temiannouncer.google.MediaRetriever;
import com.robosolutions.temiannouncer.viewmodel.SharedViewModel;

import java.util.Collections;
import java.util.logging.Logger;

public class TaskPopup {
    private static final Logger LOGGER = Logger.getLogger(TaskPopup.class.getName());
    private Fragment parent;
    private Dialog dialog;
    private DriveServiceHelper mDriveServiceHelper;
    private SharedViewModel viewModel;

    public TaskPopup(Fragment frag) {
        this.parent = frag;
        this.dialog = new Dialog(parent.getContext());
        this.viewModel = new ViewModelProvider(parent.getActivity()).get(SharedViewModel.class);
        GoogleAccountCredential credential =
                GoogleAccountCredential.usingOAuth2(
                        parent.getContext(), Collections.singleton(DriveScopes.DRIVE_FILE));

        credential.setSelectedAccount(viewModel.getGoogleSignInAccount().getAccount());
        Drive googleDriveService = new Drive.Builder(
                AndroidHttp.newCompatibleTransport(),
                new GsonFactory(),
                credential
        ).build();
        mDriveServiceHelper = new DriveServiceHelper(googleDriveService);
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
