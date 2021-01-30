package com.robosolutions.temiannouncer.views;
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
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.robosolutions.temiannouncer.R;
import com.robosolutions.temiannouncer.model.TemiTask;
import com.robosolutions.temiannouncer.temi.TemiController;
import com.robosolutions.temiannouncer.viewmodel.SharedViewModel;
import com.robosolutions.temiannouncer.views.adapters.TemiTaskAdapter;

public class TaskFragment extends Fragment {
    private NavController navController;
    private ImageView saveBtn, backBtn, addActionBtn;
    private EditText taskTitleInput;
    private SharedViewModel viewModel;
    private TaskPopup popup;
    private TemiTaskAdapter temiTaskAdapter;
    private TemiController controller;

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
        controller = TemiController.getInstance();
        temiTaskAdapter = new TemiTaskAdapter(getContext(), controller.getSavedLocations());

        saveBtn = view.findViewById(R.id.saveTaskBtn);
        taskTitleInput = view.findViewById(R.id.taskIdInput);
        backBtn = view.findViewById(R.id.taskBackBtn);
        addActionBtn = view.findViewById(R.id.addActionBtn);
        viewModel = new ViewModelProvider(this.getActivity()).get(SharedViewModel.class);
        popup = new TaskPopup(this);

        addActionBtn.setOnClickListener(v -> {
            popup.showPromptPage();
        });

        backBtn.setOnClickListener(v -> {
            navController.navigate(R.id.action_taskFragment_to_homeFragment);
        });

        saveBtn.setOnClickListener(v -> {
            TemiTask temiTask = new TemiTask(taskTitleInput.getText().toString(), R.drawable.home_temi_logo);
            viewModel.insertTask(temiTask);
            System.out.println("Inserted: " + temiTask);
            navController.navigate(R.id.action_taskFragment_to_homeFragment);
        });
    }
}