package com.robosolutions.temiannouncer.views;

import android.app.Dialog;
import android.widget.Button;
import android.widget.ImageView;
import com.robosolutions.temiannouncer.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class TaskPopup extends AppCompatActivity {

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
