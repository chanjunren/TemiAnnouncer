package com.robosolutions.temiannouncer.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.robosolutions.temiannouncer.R;
import com.robosolutions.temiannouncer.model.TemiStep;

import java.util.ArrayList;
import java.util.List;

public class TemiTaskAdapter extends RecyclerView.Adapter<TemiTaskAdapter.StepViewHolder> {
    private Context context;
    private List<TemiStep> steps;
    private List<String> locations;
    private StepViewHolder holder;

    public TemiTaskAdapter(Context context, List<String> locations) {
        this.context = context;
        this.steps = new ArrayList<>();
        this.locations = locations;
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

    @Override
    public int getItemCount() {
        return steps.size();
    }

    // ViewHolder for one card
    public class StepViewHolder extends RecyclerView.ViewHolder {
        ImageView midActionThumbnail;
        ImageView destActionThumbnail;
        Spinner locSpinner;
        ArrayAdapter<String> spinnerAdapter;

        public StepViewHolder(@NonNull View itemView) {
            super(itemView);
            midActionThumbnail = itemView.findViewById(R.id.midActionImg);
            destActionThumbnail = itemView.findViewById(R.id.destActionImg);
            locSpinner = itemView.findViewById(R.id.locSpinner);

            spinnerAdapter = new ArrayAdapter<String>(context,
                    android.R.layout.simple_spinner_item, locations);

            spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            locSpinner.setAdapter(holder.spinnerAdapter);
        }
    }
}
