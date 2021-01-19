package com.robosolutions.temiannouncer.views;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.robosolutions.temiannouncer.R;

public class ResourcesFragment extends Fragment {
    public ResourcesFragment() {
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
        View v = inflater.inflate(R.layout.fragment_resources, container, false);

        v.findViewById(R.id.resBackBtn).setOnClickListener(backBtn -> {
            NavController navController = Navigation.findNavController(v);
            navController.navigate(R.id.action_resourcesFragment_to_homeFragment);
        });
        return v;
    }
}