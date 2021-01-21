package com.robosolutions.temiannouncer.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.robosolutions.temiannouncer.R;
import com.robosolutions.temiannouncer.model.Task;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    Context mContext;
    List<Task> mData;

    public Adapter(Context mContext, List<Task> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.task_card_item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.cardBg.setImageResource(mData.get(position).getBackground());
        holder.titleView.setText(mData.get(position).getTaskId());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    // ViewHolder for one card
    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView cardBg;
        TextView titleView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cardBg = itemView.findViewById(R.id.taskCardPreview);
            titleView = itemView.findViewById(R.id.taskCardTitle);
        }
    }
}
