package com.robosolutions.temiannouncer.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.robosolutions.temiannouncer.R;

public class HomeFragment extends Fragment implements View.OnClickListener {
    NavController navController;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);

        view.findViewById(R.id.taskPageButton).setOnClickListener(this);
        view.findViewById(R.id.configurationsPageButton).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if (viewId == R.id.taskPageButton) {
            navController.navigate(R.id.action_homeFragment_to_newTaskFragment);
        } else if (viewId == R.id.configurationsPageButton) {
            navController.navigate(R.id.action_homeFragment_to_configurationFragment);
        }
    }
}