package com.robosolutions.temiannouncer.views;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.robosolutions.temiannouncer.R;
import com.robosolutions.temiannouncer.model.TemiStep;
import com.robosolutions.temiannouncer.model.TemiTask;
import com.robosolutions.temiannouncer.temi.TemiController;
import com.robosolutions.temiannouncer.viewmodel.SharedViewModel;
import com.robosolutions.temiannouncer.views.adapters.TemiTaskAdapter;

import java.util.ArrayList;

public class TaskFragment extends Fragment {
    private NavController navController;
    private ImageView saveBtn, backBtn;
    private EditText taskTitleInput;
    private SharedViewModel viewModel;
    private TaskPopup popup;
    private TemiTaskAdapter temiTaskAdapter;
    private TemiController controller;
    private ArrayList<TemiStep> steps;
    private Button addStepBtn;
    private RecyclerView stepsRv;

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
        popup = new TaskPopup(this);

        this.steps = new ArrayList<>();
        steps.add(new TemiStep());

        temiTaskAdapter = new TemiTaskAdapter(getContext(), controller.getSavedLocations(),
                steps, popup);

        saveBtn = view.findViewById(R.id.saveTaskBtn);
        taskTitleInput = view.findViewById(R.id.taskIdInput);
        addStepBtn = view.findViewById(R.id.addStepBtn);
        stepsRv = view.findViewById(R.id.stepsRv);
        backBtn = view.findViewById(R.id.taskBackBtn);
        viewModel = new ViewModelProvider(this.getActivity()).get(SharedViewModel.class);

        stepsRv.setAdapter(temiTaskAdapter);
        stepsRv.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false));

        addStepBtn.setOnClickListener(v -> {
            temiTaskAdapter.addStep();
        });
        backBtn.setOnClickListener(v -> {
            navController.navigate(R.id.action_taskFragment_to_homeFragment);
        });

        saveBtn.setOnClickListener(v -> {
//            TemiTask temiTask = new TemiTask(taskTitleInput.getText().toString(), R.drawable.home_temi_logo);
//            viewModel.insertTask(temiTask);
//            System.out.println("Inserted: " + temiTask);
            navController.navigate(R.id.action_taskFragment_to_homeFragment);
        });
    }

}