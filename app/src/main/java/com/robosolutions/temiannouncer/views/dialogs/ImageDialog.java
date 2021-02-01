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
import android.widget.Toast;

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

public class ImageDialog extends DialogFragment {
    private static final String TAG = "ImageDialog";

    private NavController navController;
    private DriveServiceHelper mDriveServiceHelper;
    private SharedViewModel viewModel;
    private ImageView exitBtn;
    private Button selectImgBtn, createActionBtn;

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

        return inflater.inflate(R.layout.dialog_img, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        exitBtn = view.findViewById(R.id.exitPopupTaskBtn);
        exitBtn.setOnClickListener(v -> {
            dismiss();
        });

        selectImgBtn = view.findViewById(R.id.selectImgBtn);
        createActionBtn = view.findViewById(R.id.createActionBtn);
        selectImgBtn.setOnClickListener(v -> {
            openFilePickerForImg();
        });
        createActionBtn.setOnClickListener(v -> {
            // To be written
        });
    }

    private void openFilePickerForImg() {
        if (mDriveServiceHelper != null) {
            Intent pickerIntent = mDriveServiceHelper.createImgPickerIntent();
            ActivityResultLauncher<Intent> pickerLauncher = registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        if (result.getResultCode() == Activity.RESULT_OK ||
                                result.getResultCode() == Activity.RESULT_FIRST_USER) {
                            Log.i(TAG, "File picker successfully launched");
                            Intent data = result.getData();
                            Uri uri = data.getData();
                            if (uri != null) {
                                createImgActionFromFilePicker(uri);
                            } else {
                                Log.e(TAG, "Uri opened is null");
                            }
                        } else {
                            Log.e(TAG, "File picker launcher failed");
                        }
                    });
            pickerLauncher.launch(pickerIntent);
        }  else {
            Log.e(TAG, "mDriveService is null");
        }
    }

    /**
     * Opens a file from its {@code uri} returned from the Storage Access Framework file picker
     * initiated by {@link #openFilePickerForImg()}.
     */
    private void createImgActionFromFilePicker(Uri uri) {
        if (mDriveServiceHelper != null) {
            Log.d(TAG, "Opening " + uri.getPath());

            mDriveServiceHelper.downloadImgUsingStorageAccessFramework(
                getActivity().getContentResolver(), uri)
                .addOnSuccessListener(imageAction -> {
                    Log.i(TAG, "Saving === >\n " + imageAction.toString());
                    viewModel.saveCurrentAction(imageAction);
                })
                .addOnFailureListener(exception -> {
                    Log.e(TAG, "Unable to open file from picker.", exception);
                    Toast.makeText(getContext(),"Unable to open file",
                            Toast.LENGTH_SHORT).show();
                });

        } else {
            Log.e(TAG, "mDriveService is null");
        }
    }
}
