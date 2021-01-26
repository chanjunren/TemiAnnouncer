package com.robosolutions.temiannouncer.views;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.robosolutions.temiannouncer.R;
import com.robosolutions.temiannouncer.model.Task;
import com.robosolutions.temiannouncer.viewmodel.MyViewModel;

public class TaskFragment extends Fragment {
    private static final int IMG_REQ_CODE = 1;
    private static final int VID_REQ_CODE = 2;

    private NavController navController;
    private ImageView saveBtn, backBtn, addActionBtn;
    private EditText taskTitleInput;
    private MyViewModel viewModel;
    private TaskPopup popup;

    public TaskFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_task, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = NavHostFragment.findNavController(getParentFragment());

        saveBtn = getView().findViewById(R.id.saveTaskBtn);
        taskTitleInput = view.findViewById(R.id.taskIdInput);
        backBtn = view.findViewById(R.id.taskBackBtn);
        addActionBtn = view.findViewById(R.id.addTaskBtn);
        viewModel = new ViewModelProvider(this.getActivity()).get(MyViewModel.class);
        popup = new TaskPopup(this);

        addActionBtn.setOnClickListener(v -> {
            popup.showPromptPage();
        });

        backBtn.setOnClickListener(v -> {
            navController.navigate(R.id.action_taskFragment_to_homeFragment);
        });

        saveBtn.setOnClickListener(v -> {
            Task task = new Task(taskTitleInput.getText().toString(), R.drawable.home_temi_logo);
            viewModel.insertTask(task);
            System.out.println("Inserted: " + task);
            navController.navigate(R.id.action_taskFragment_to_homeFragment);
        });
    }
}