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

public class HomepageStudent extends AppCompatActivity {
    private RecyclerView.Adapter adapter2;
    private RecyclerView recyclerView_taskList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hompeage_student);
        recyclerView_Popular();

    }

    public void OnClicklogOut(View view){
        AlertDialog logout1 = new AlertDialog.Builder(HomepageStudent.this).create();
        logout1.setTitle("Log out");
        logout1.setTitle("Are you sure you want to log out?");
        logout1.setButton(AlertDialog.BUTTON_POSITIVE, "Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(HomepageStudent.this, Welcome.class);
                startActivity(intent);
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

        ArrayList<Task> task = new ArrayList<>();
        task.add(new Task("Task 1", "work_process_img"));
        task.add(new Task("Task 2", "work_process_img"));
        task.add(new Task("Task 3", "work_process_img"));
        task.add(new Task("Task 4", "work_process_img"));

        adapter2 = new TasksAdapter(task);
        recyclerView_taskList.setAdapter(adapter2);
    }
}