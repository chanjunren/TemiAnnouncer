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


//    private void showPromptPageForResult() {
//        dialog.setContentView(R.layout.popup_task);

//        dialog.show();
//    }
//
//    private void showImgPage() {
//        dialog.setContentView(R.layout.popup_img);
//        ImageView exitPrompt = dialog.findViewById(R.id.exitPopupTaskBtn);
//        exitPrompt.setOnClickListener(v -> {
//            dialog.dismiss();
//        });
//
//        Button selectImgBtn = dialog.findViewById(R.id.selectImgBtn);
//        Button createActionBtn = dialog.findViewById(R.id.createActionBtn);
//        selectImgBtn.setOnClickListener(v -> {
//            openFilePickerForImg();
//        });
//    }
//
//    private void showVideoPage() {
//        dialog.setContentView(R.layout.popup_video);
//        ImageView exitPrompt = dialog.findViewById(R.id.exitPopupTaskBtn);
//        exitPrompt.setOnClickListener(v -> {
//            dialog.dismiss();
//        });
//
//        Button selectVidBtn = dialog.findViewById(R.id.selectVidBtn);
//        Button createActionBtn = dialog.findViewById(R.id.createActionBtn);
//        selectVidBtn.setOnClickListener(v -> {
//            openFilePickerForVid();
//        });
//
//    }
//
//    private void showSpeechPage() {
//        dialog.setContentView(R.layout.popup_speech);
//        ImageView exitPrompt = dialog.findViewById(R.id.exitPopupTaskBtn);
//        exitPrompt.setOnClickListener(v -> {
//            dialog.dismiss();
//        });
//    }
//
//    private void openFilePickerForImg() {
//        if (mDriveServiceHelper != null) {
//            Intent pickerIntent = mDriveServiceHelper.createImgPickerIntent();
//            ActivityResultLauncher<Intent> pickerLauncher = parent.registerForActivityResult(
//                    new ActivityResultContracts.StartActivityForResult(),
//                    result -> {
//                        if (result.getResultCode() == Activity.RESULT_OK ||
//                                result.getResultCode() == Activity.RESULT_FIRST_USER) {
//                            Log.i(TAG, "File picker successfully launched");
//                            Intent data = result.getData();
//                            Uri uri = data.getData();
//                            if (uri != null) {
//                                createImgActionFromFilePicker(uri);
//                            } else {
//                                Log.e(TAG, "Uri opened is null");
//                            }
//                        } else {
//                            Log.e(TAG, "File picker launcher failed");
//                        }
//                    }
//            );
//            pickerLauncher.launch(pickerIntent);
//        } else {
//            Log.e(TAG, "mDriveServiceHelper is null");
//        }
//    }
//
//    /**
//     * Opens a file from its {@code uri} returned from the Storage Access Framework file picker
//     * initiated by {@link #openFilePickerForImg()}.
//     */
//    private void createImgActionFromFilePicker(Uri uri) {
//        if (mDriveServiceHelper != null) {
//            Log.d(TAG, "Opening " + uri.getPath());
//
//            mDriveServiceHelper.downloadImgUsingStorageAccessFramework(
//                    parent.getActivity().getContentResolver(), uri)
//                    .addOnSuccessListener(taskObj -> {
//                        Log.i(TAG, taskObj.toString());
//                    })
//                    .addOnFailureListener(exception -> {
//                        Log.e(TAG, "Unable to open file from picker.", exception);
//                        Toast.makeText(parent.getContext(),"Unable to open file",
//                                Toast.LENGTH_SHORT).show();
//                    });
//
//        }
//    }
//
//    private void openFilePickerForVid() {
//        if (mDriveServiceHelper != null) {
//            Intent pickerIntent = mDriveServiceHelper.createVidPickerIntent();
//            ActivityResultLauncher<Intent> pickerLauncher = parent.registerForActivityResult(
//                    new ActivityResultContracts.StartActivityForResult(),
//                    result -> {
//                        if (result.getResultCode() == Activity.RESULT_OK ||
//                                result.getResultCode() == Activity.RESULT_FIRST_USER) {
//                            Log.i(TAG, "File picker successfully launched");
//                            Intent data = result.getData();
//                            Uri uri = data.getData();
//                            if (uri != null) {
//                                downloadVidFromFilePicker(uri);
//                            } else {
//                                Log.e(TAG, "Uri opened is null");
//                            }
//                        } else {
//                            Log.e(TAG, "File picker launcher failed");
//                        }
//                    }
//            );
//            pickerLauncher.launch(pickerIntent);
//        }
//    }
//
//    /**
//     * Opens a file from its {@code uri} returned from the Storage Access Framework file picker
//     * initiated by {@link #openFilePickerForImg()}.
//     */
//    private void downloadVidFromFilePicker(Uri uri) {
//        if (mDriveServiceHelper != null) {
//            Log.d(TAG, "Opening " + uri.getPath());
//
//            mDriveServiceHelper.downloadVidUsingStorageAccessFramework(
//                    parent.getActivity().getContentResolver(), uri)
//                    .addOnSuccessListener(nameAndPath -> {
//                        String name = nameAndPath.first;
//                        String path = nameAndPath.second;
//
//                        Log.i(TAG, name + " download to " + path);
//                    })
//                    .addOnFailureListener(exception ->
//                            Log.e(TAG, "Unable to open file from picker.", exception));
//        }
//    }

}
