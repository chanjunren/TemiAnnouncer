package com.robosolutions.temiannouncer.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.robosolutions.temiannouncer.R;
import com.robosolutions.temiannouncer.model.Task;
import com.robosolutions.temiannouncer.viewmodel.MyViewModel;

import java.util.ArrayList;
import java.util.List;

public class SavedFragment extends Fragment {

    private ImageView backBtn;
    private NavController navController;
    private RecyclerView rv;
    private Adapter adapter;
    private MyViewModel viewModel;

    public SavedFragment() {
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
        return inflater.inflate(R.layout.fragment_saved, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = NavHostFragment.findNavController(getParentFragment());
        backBtn = view.findViewById(R.id.savedBackBtn);
        rv = view.findViewById(R.id.savedCardsRv);
        viewModel = new ViewModelProvider(this.getActivity()).get(MyViewModel.class);

        final Observer<List<Task>> taskListObserver = new Observer<List<Task>>() {
            @Override
            public void onChanged(List<Task> tasks) {
                updateTaskViews((ArrayList<Task>) tasks);
            }
        };

        backBtn.setOnClickListener(v -> {
            navController.navigate(R.id.action_savedFragment_to_homeFragment);
        });

        viewModel.getTaskLiveData().observe(getActivity(), taskListObserver);

    }

    public void updateTaskViews(ArrayList<Task> tasks) {
        adapter = new Adapter(getActivity(), tasks);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}