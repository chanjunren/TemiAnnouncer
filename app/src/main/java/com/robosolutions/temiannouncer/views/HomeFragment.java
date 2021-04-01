package com.robosolutions.temiannouncer.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.robosolutions.temiannouncer.R;

public class HomeFragment extends Fragment implements View.OnClickListener {

    public HomeFragment() {
        // Required empty public constructor
    }

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
        view.findViewById(R.id.taskPageBtn).setOnClickListener(this);
        view.findViewById(R.id.confPageBtn).setOnClickListener(this);
        view.findViewById(R.id.savedPageBtn).setOnClickListener(this);
        view.findViewById(R.id.historyPageBtn).setOnClickListener(this);
        view.findViewById(R.id.resPageBtn).setOnClickListener(this);
        view.findViewById(R.id.mapPageBtn).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if (viewId == R.id.taskPageBtn) {
            navController.navigate(R.id.action_homeFragment_to_newTaskFragment);
        } else if (viewId == R.id.mapPageBtn) {
            navController.navigate(R.id.action_homeFragment_to_mapFragment);
        } else if (viewId == R.id.confPageBtn) {
            navController.navigate(R.id.action_homeFragment_to_configurationFragment);
        } else if (viewId == R.id.savedPageBtn) {
            navController.navigate(R.id.action_homeFragment_to_savedFragment);
        } else if (viewId == R.id.historyPageBtn) {
            navController.navigate(R.id.action_homeFragment_to_historyFragment);
        } else if (viewId == R.id.resPageBtn) {
            navController.navigate(R.id.action_homeFragment_to_resourcesFragment);
        }
    }
}