package com.robosolutions.temiannouncer.views;

import android.app.Dialog;
import android.widget.ImageView;

import com.robosolutions.temiannouncer.R;
import androidx.fragment.app.FragmentActivity;

public class TaskPopup {
    private FragmentActivity parent;
    private Dialog dialog;

    public TaskPopup(FragmentActivity fragAct) {
        parent = fragAct;
        dialog = new Dialog(fragAct);

    }

    public void showPromptPage() {
        dialog.setContentView(R.layout.popup_task);
        ImageView mediaPopupBtn = dialog.findViewById(R.id.showMediaPopupBtn);
        ImageView speechPopupBtn = dialog.findViewById(R.id.showSpeechPopupBtn);
        ImageView exitPrompt = dialog.findViewById(R.id.exitPopupTaskBtn);
        mediaPopupBtn.setOnClickListener(v -> {
            showMediaPage();
        });
        speechPopupBtn.setOnClickListener(v -> {
            showSpeechPage();
        });
        exitPrompt.setOnClickListener(v -> {
            dialog.dismiss();
        });
        dialog.show();
    }

    private void showMediaPage() {
        dialog.setContentView(R.layout.popup_media);
        ImageView exitPrompt = dialog.findViewById(R.id.exitPopupTaskBtn);
        exitPrompt.setOnClickListener(v -> {
            dialog.dismiss();
        });

    }

    private void showSpeechPage() {
        dialog.setContentView(R.layout.popup_speech);
        ImageView exitPrompt = dialog.findViewById(R.id.exitPopupTaskBtn);
        exitPrompt.setOnClickListener(v -> {
            dialog.dismiss();
        });
    }


}
