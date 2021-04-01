package com.robosolutions.temiannouncer.views.dialogs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.robosolutions.temiannouncer.R;

// onAttach -> onCreate -> onCreateDialog -> onCreateView -> onViewCreated -> onDestroy.
public class ActionDialog extends DialogFragment {
    private static final String TAG = "SelectActionDialog";
    private NavController navController;
    private ImageView imgPopupBtn, videoPopupBtn, speechPopupBtn, exitBtn;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        navController = NavHostFragment.findNavController(this);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_select_task, container);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imgPopupBtn = view.findViewById(R.id.showImgPopupBtn);
        videoPopupBtn = view.findViewById(R.id.showVideoPopupBtn);
        speechPopupBtn = view.findViewById(R.id.showSpeechPopupBtn);
        exitBtn = view.findViewById(R.id.exitPopupTaskBtn);
        imgPopupBtn.setOnClickListener(v -> {
            navController.navigate(R.id.action_taskDialogFragment_to_imageDialog);
        });
        videoPopupBtn.setOnClickListener(v -> {
            navController.navigate(R.id.action_taskDialogFragment_to_videoDialog);
        });
        speechPopupBtn.setOnClickListener(v -> {
            navController.navigate(R.id.action_taskDialogFragment_to_speechDialog);
        });
        exitBtn.setOnClickListener(v -> {
            dismiss();
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
