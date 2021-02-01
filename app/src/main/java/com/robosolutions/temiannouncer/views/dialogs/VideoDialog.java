package com.robosolutions.temiannouncer.views.dialogs;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.robosolutions.temiannouncer.R;
import com.robosolutions.temiannouncer.google.DriveServiceHelper;
import com.robosolutions.temiannouncer.viewmodel.SharedViewModel;

public class VideoDialog extends DialogFragment {
    private static final String TAG = "VideoDialog";

    private NavController navController;
    private DriveServiceHelper mDriveServiceHelper;
    private SharedViewModel viewModel;
    private ImageView exitBtn;
    private Button selectVidBtn, createActionBtn;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        navController = NavHostFragment.findNavController(this);
        this.viewModel = new ViewModelProvider(getActivity()).get(SharedViewModel.class);
        this.mDriveServiceHelper = viewModel.getmDriveServiceHelper();
        mDriveServiceHelper.setContext(getContext());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_video, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        exitBtn = view.findViewById(R.id.exitPopupTaskBtn);
        selectVidBtn = view.findViewById(R.id.selectVidBtn);
        createActionBtn = view.findViewById(R.id.createActionBtn);

        exitBtn.setOnClickListener(v -> {
            dismiss();
        });
        selectVidBtn.setOnClickListener(v -> {
            openFilePickerForVid();
        });

        createActionBtn.setOnClickListener(v-> {

        });
    }

    private void openFilePickerForVid() {
        if (mDriveServiceHelper != null) {
            Intent pickerIntent = mDriveServiceHelper.createVidPickerIntent();
            ActivityResultLauncher<Intent> pickerLauncher = registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        if (result.getResultCode() == Activity.RESULT_OK ||
                                result.getResultCode() == Activity.RESULT_FIRST_USER) {
                            Log.i(TAG, "File picker successfully launched");
                            Intent data = result.getData();
                            Uri uri = data.getData();
                            if (uri != null) {
                                downloadVidFromFilePicker(uri);
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

    /**
     * Opens a file from its {@code uri} returned from the Storage Access Framework file picker
     * initiated by {@link #openFilePickerForVid()}.
     */
    private void downloadVidFromFilePicker(Uri uri) {
        if (mDriveServiceHelper != null) {
            Log.d(TAG, "Opening " + uri.getPath());

            mDriveServiceHelper.downloadVidUsingStorageAccessFramework(
                    getActivity().getContentResolver(), uri)
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
