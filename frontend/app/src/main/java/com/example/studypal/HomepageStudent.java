package com.example.studypal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

<<<<<<< HEAD
import java.util.ArrayList;

public class HomepageStudent extends AppCompatActivity {
<<<<<<< HEAD

    private RecyclerView.Adapter adapter2;
    private RecyclerView recyclerView_taskList;

=======
    private RecyclerView.Adapter adapter2;
    private RecyclerView recyclerView_taskList;
>>>>>>> c42b3703457acf1a776de5f77afd684793c7a6fe
=======


public class HomepageStudent extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private RecyclerView.Adapter adapter2;
    private RecyclerView recyclerView_taskList;
    ArrayList<Task> task;

    public RecyclerView.Adapter getAdapter2() {
        return adapter2;
    }

    public void setAdapter2(RecyclerView.Adapter adapter2) {
        this.adapter2 = adapter2;
    }

    public RecyclerView getRecyclerView_taskList() {
        return recyclerView_taskList;
    }

    public void setRecyclerView_taskList(RecyclerView recyclerView_taskList) {
        this.recyclerView_taskList = recyclerView_taskList;
    }

    public ArrayList<Task> getTask() {
        return task;
    }

    public void setTask(ArrayList<Task> task) {
        this.task = task;
    }

>>>>>>> a9cb39947c394d82d0ec9007f72b45d7ec44d8c5
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hompeage_student);
<<<<<<< HEAD

        recyclerView_Popular();
=======
        recyclerView_Popular();

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onBackPressed() {
    // empty so nothing happens
    }

    public void OnClicklogOut(View view){
        AlertDialog logout1 = new AlertDialog.Builder(HomepageStudent.this).create();
        logout1.setTitle("Log out");
        logout1.setTitle("Are you sure you want to log out?");
        logout1.setButton(AlertDialog.BUTTON_POSITIVE, "Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
              FirebaseAuth.getInstance().signOut();
            }
        });
        logout1.setButton(AlertDialog.BUTTON_NEGATIVE, "No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        logout1.show();

    }

    private void recyclerView_Popular() {

        // Creates a horizontal LinearLayoutManager to enable to move between tasks
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView_taskList = findViewById(R.id.recyclerView);
        recyclerView_taskList.setLayoutManager(linearLayoutManager);

        task = new ArrayList<>();
        task.add(new Task("Task 1", "work_process_img"));
        task.add(new Task("Task 2", "work_process_img"));
        task.add(new Task("Task 3", "work_process_img"));
        task.add(new Task("Task 4", "work_process_img"));

        adapter2 = new TasksAdapter(task);
        recyclerView_taskList.setAdapter(adapter2);
>>>>>>> c42b3703457acf1a776de5f77afd684793c7a6fe
    }

    private void recyclerView_Popular() {

        // Creates a horizontal LinearLayoutManager to enable to move between tasks
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView_taskList = findViewById(R.id.recyclerView);
        recyclerView_taskList.setLayoutManager(linearLayoutManager);

        ArrayList<Task> task = new ArrayList<>();
        task.add(new Task("Task 1", "work_process_img"));
        task.add(new Task("Task 2", "work_process_img"));
        task.add(new Task("Task 3", "work_process_img"));
        task.add(new Task("Task 4", "work_process_img"));

        adapter2 = new TasksAdapter(task);
        recyclerView_taskList.setAdapter(adapter2);
    }


}