package com.robosolutions.temiannouncer.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.robosolutions.temiannouncer.R;
import com.robosolutions.temiannouncer.model.TemiStep;
import com.robosolutions.temiannouncer.model.actions.TemiAction;
import com.robosolutions.temiannouncer.views.dialogs.SelectActionDialog;

import java.util.ArrayList;
import java.util.List;

public class TemiTaskAdapter extends RecyclerView.Adapter<TemiTaskAdapter.StepViewHolder> {
    private Context context;
    private List<TemiStep> steps;
    private List<String> locations;
    private StepViewHolder holder;
    private NavController navController;

    public TemiTaskAdapter(Context context, List<String> locations, List<TemiStep> steps,
                           NavController controller) {
        this.context = context;
        this.steps = steps;
        this.locations = locations;
        this.navController = controller;
    }

    @NonNull
    @Override
    public StepViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.card_item_temi_step, parent, false);
        return new StepViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull StepViewHolder holder, int position) {
        // Set all the views of the card
        this.holder = holder;
    }

    public void addStep() {
        this.steps.add(new TemiStep());
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return steps.size();
    }

    // ViewHolder for one card
    public class StepViewHolder extends RecyclerView.ViewHolder {
        private ArrayList<TemiAction> actions;
        ImageView midActionThumbnail, destActionThumbnail, addMidActionBtn, addDestActionBtn;
        Spinner locSpinner;
        ArrayAdapter<String> spinnerAdapter;
        int index;

        public StepViewHolder(@NonNull View itemView) {
            super(itemView);
            midActionThumbnail = itemView.findViewById(R.id.midActionImg);
            destActionThumbnail = itemView.findViewById(R.id.destActionImg);
            addMidActionBtn = itemView.findViewById(R.id.addMidActionBtn);
            addDestActionBtn = itemView.findViewById(R.id.addDestActionBtn);
            locSpinner = itemView.findViewById(R.id.locSpinner);

            spinnerAdapter = new ArrayAdapter<String>(context,
                    android.R.layout.simple_spinner_item, locations);

            spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            locSpinner.setAdapter(spinnerAdapter);

            addMidActionBtn.setOnClickListener(v -> {
                navController.navigate(R.id.action_taskFragment_to_taskDialogFragment);
            });

            addDestActionBtn.setOnClickListener(v -> {
                navController.navigate(R.id.action_taskFragment_to_taskDialogFragment);
            });
        }
    }
}
