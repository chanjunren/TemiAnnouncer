package com.robosolutions.temiannouncer.views;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.robosolutions.temiannouncer.R;

public class TaskFragment extends Fragment {
    private NavController navController;
    private ImageView saveBtn, backBtn;
    private TextView taskTitleInput;

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
        taskTitleInput = view.findViewById(R.id.sequenceIdInput);
        backBtn = view.findViewById(R.id.taskBackBtn);

        backBtn.setOnClickListener(v -> {
            navController.navigate(R.id.action_taskFragment_to_homeFragment);
        });

        saveBtn.setOnClickListener(v -> {
            navController.navigate(R.id.action_taskFragment_to_homeFragment);
        });
    }
}