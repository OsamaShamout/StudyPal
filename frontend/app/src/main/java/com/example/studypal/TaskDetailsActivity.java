package com.example.studypal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class TaskDetailsActivity extends AppCompatActivity {

    private TextView name;
    private TextView type;
    private TextView section;
    private TextView progress;
    private TextView studyHours;
    private TextView status;
    private TextView material;

    private Task object;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_details);

        name = findViewById(R.id.name);
        type = findViewById(R.id.type);
        section = findViewById(R.id.section);
        progress = findViewById(R.id.progress);
        studyHours = findViewById(R.id.studyHours);
        status = findViewById(R.id.status);
        material = findViewById(R.id.material);

        getBundle();
    }

    public void getBundle(){
        object = (Task) getIntent().getSerializableExtra("object");

        name.setText(object.getName());
        type.setText(object.getType());
        section.setText(object.getSection());
        progress.setText(String.valueOf(object.getProgress()));
        studyHours.setText(String.valueOf(object.getStudy_hours()));
        status.setText(object.getStatus());
        material.setText(object.getMaterials());

    }
}