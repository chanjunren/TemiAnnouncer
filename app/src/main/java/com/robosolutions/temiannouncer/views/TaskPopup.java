package com.robosolutions.temiannouncer.views;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.common.api.Scope;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.robosolutions.temiannouncer.R;
import com.robosolutions.temiannouncer.google.DriveServiceHelper;
import com.robosolutions.temiannouncer.viewmodel.SharedViewModel;

import java.util.Collections;

public class TaskPopup {
    private static final String TAG = "TaskPopup";
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

        Log.i(TAG, "Account: " + viewModel.getGoogleSignInAccount().getAccount());
        credential.setSelectedAccount(viewModel.getGoogleSignInAccount().getAccount());
        Drive googleDriveService = new Drive.Builder(
                AndroidHttp.newCompatibleTransport(),
                new GsonFactory(),
                credential)
            .build();
        mDriveServiceHelper = new DriveServiceHelper(googleDriveService, parent.getContext());
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
            openFilePicker();
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

    private void openFilePicker() {
        if (mDriveServiceHelper != null) {
            Intent pickerIntent = mDriveServiceHelper.createImgPickerIntent();
            ActivityResultLauncher<Intent> pickerLauncher = parent.registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        if (result.getResultCode() == Activity.RESULT_OK ||
                                result.getResultCode() == Activity.RESULT_FIRST_USER) {
                            Log.i(TAG, "File picker successfully launched");
                            Intent data = result.getData();
                            Uri uri = data.getData();
                            if (uri != null) {
                                downloadFileFromFilePicker(uri);
                            } else {
                                Log.e(TAG, "Uri opened is null");
                            }
                        } else {
                            Log.e(TAG, "File picker launcher failed");
                        }
                    }
            );
            pickerLauncher.launch(pickerIntent);
        }
    }

//    /**
//     * Opens a file from its {@code uri} returned from the Storage Access Framework file picker
//     * initiated by {@link #openFilePicker()}.
//     */
//    private void openFileFromFilePicker(Uri uri) {
//        Log.i(TAG, "opening " + uri.getPath());
//        mDriveServiceHelper.openFileUsingStorageAccessFramework(
//                parent.getActivity().getContentResolver(), uri)
//                .addOnSuccessListener(nameAndContent -> {
//                    String name = nameAndContent.first;
//                    String content = nameAndContent.second;
//
//                    Log.i(TAG, "Name: " + name + " content: " + content);
//                });
//    }
    /**
     * Opens a file from its {@code uri} returned from the Storage Access Framework file picker
     * initiated by {@link #openFilePicker()}.
     */
    private void downloadFileFromFilePicker(Uri uri) {
        if (mDriveServiceHelper != null) {
            Log.d(TAG, "Opening " + uri.getPath());

            mDriveServiceHelper.downloadFileUsingStorageAccessFramework(
                    parent.getActivity().getContentResolver(), uri)
                    .addOnSuccessListener(nameAndPath -> {
                        String name = nameAndPath.first;
                        String path = nameAndPath.second;

                        Log.i(TAG, name + " download to " + path);
                    })
                    .addOnFailureListener(exception ->
                            Log.e(TAG, "Unable to open file from picker.", exception));
        }
    }

}
