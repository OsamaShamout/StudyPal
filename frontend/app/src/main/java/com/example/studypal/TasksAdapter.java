package com.example.studypal;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.ViewHolder> {
    ArrayList<Task> tasksDomains; // array list of the the class task

    public TasksAdapter(ArrayList<Task> tasksDomains) { // constructor
        this.tasksDomains = tasksDomains;
    }

    @NonNull
    @Override
    public TasksAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // inflate our layout
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_viewholder_task, parent,false);
        return new TasksAdapter.ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull TasksAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.taskName.setText(tasksDomains.get(position).getName());

        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(tasksDomains.get(position).getImg(), "drawable", holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext()).load(drawableResourceId).into(holder.taskPic);

        holder.showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), TaskDetailsActivity.class);
                intent.putExtra("object", tasksDomains.get(position));
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tasksDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // creating variables for our views
        TextView taskName;
        ImageView taskPic;
        TextView showButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // initializing our views with their ids
            taskName = itemView.findViewById(R.id.taskName);
            taskPic = itemView.findViewById(R.id.imageTask);
            showButton = itemView.findViewById(R.id.showButton);
        }
    }
}
