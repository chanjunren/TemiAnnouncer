package com.robosolutions.temiannouncer.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.robosolutions.temiannouncer.R;

public class ResourcesFragment extends Fragment {

    private ImageView backBtn;
    private NavController navController;

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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = NavHostFragment.findNavController(getParentFragment());
        backBtn = view.findViewById(R.id.resBackBtn);

        backBtn.setOnClickListener(v -> {
            navController.navigate(R.id.action_resourcesFragment_to_homeFragment);
        });
    }
}