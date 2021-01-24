package com.robosolutions.temiannouncer.views;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.jaiselrahman.filepicker.activity.FilePickerActivity;
import com.jaiselrahman.filepicker.config.Configurations;
import com.jaiselrahman.filepicker.model.MediaFile;
import com.robosolutions.temiannouncer.R;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import java.util.ArrayList;

public class TaskPopup extends AppCompatActivity {
    private static final int IMG_REQ_CODE = 1;
    private static final int VID_REQ_CODE = 2;

    private Fragment parent;
    private Dialog dialog;

    public TaskPopup(Fragment frag) {
        this.parent = frag;
        dialog = new Dialog(parent.getContext());
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
            if (ContextCompat.checkSelfPermission(parent.getContext(),
                    Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                // Request permission if permission not granted
                System.out.println("I'm being executed because permission not granted");
                ActivityCompat.requestPermissions(parent.getActivity(),
                        new String[]{Manifest.permission.CAMERA}, 1);
            } else {
                pickImage();
            }
        });
    }

    public void pickImage() {
        Intent intent = new Intent(parent.getContext(), FilePickerActivity.class);
        intent.putExtra(FilePickerActivity.CONFIGS, new Configurations.Builder()
            .setCheckPermission(true)
            .setShowImages(true)
            .setShowVideos(false)
            .enableImageCapture(true)
            .setMaxSelection(1)
            .setSkipZeroSizeFiles(true)
            .build());

        ActivityResultLauncher<Intent> imgResLauncher = parent.registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    Intent data = result.getData();
                    if (result.getResultCode() == Activity.RESULT_OK && data != null) {
                        ArrayList<MediaFile> media = data.getParcelableArrayListExtra(
                                FilePickerActivity.MEDIA_FILES);
                        String path = media.get(0).getPath();
                        Toast.makeText(parent.getContext(), path,
                                Toast.LENGTH_SHORT).show();

                    }
                }
        );

        imgResLauncher.launch(intent);
    }



    private void showVideoPage() {
        dialog.setContentView(R.layout.popup_video);
        ImageView exitPrompt = dialog.findViewById(R.id.exitPopupTaskBtn);
        exitPrompt.setOnClickListener(v -> {
            dialog.dismiss();
        });

        Button pickVideoBtn = dialog.findViewById(R.id.selectVidBtn);
        pickVideoBtn.setOnClickListener(v -> {
            if (ContextCompat.checkSelfPermission(parent.getContext(),
                    Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                // Request permission if permission not granted
                ActivityCompat.requestPermissions(parent.getActivity(), new String[]{Manifest.permission.CAMERA},
                        VID_REQ_CODE);
            } else {
                pickVideo();
            }
        });
    }

    public void pickVideo() {
        Intent intent = new Intent(parent.getContext(), FilePickerActivity.class);
        intent.putExtra(FilePickerActivity.CONFIGS, new Configurations.Builder()
                .setCheckPermission(true)
                .setShowAudios(false)
                .setShowImages(false)
                .setShowVideos(true)
                .enableImageCapture(true)
                .setMaxSelection(1)
                .setSkipZeroSizeFiles(true)
                .build());

        ActivityResultLauncher<Intent> vidResLauncher = parent.registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    Intent data = result.getData();
                    if (result.getResultCode() == Activity.RESULT_OK && data != null) {
                        ArrayList<MediaFile> media = data.getParcelableArrayListExtra(
                                FilePickerActivity.MEDIA_FILES);
                        String path = media.get(0).getPath();
                        Toast.makeText(parent.getContext(), path,
                                Toast.LENGTH_SHORT).show();
                    }
                }
        );

        vidResLauncher.launch(intent);
    }

    private void showSpeechPage() {
        dialog.setContentView(R.layout.popup_speech);
        ImageView exitPrompt = dialog.findViewById(R.id.exitPopupTaskBtn);
        exitPrompt.setOnClickListener(v -> {
            dialog.dismiss();
        });

    }
}
