package com.example.studypal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;

public class Homepage extends AppCompatActivity {

    ImageButton log_out;
    TextView greetings;

    String user_id;
    String name;

    private RecyclerView.Adapter adapter2;
    private RecyclerView recyclerView_taskList;
    ArrayList<Task> task = new ArrayList<>();

    Calendar right_now;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(Homepage.this);
        user_id = preferences.getString("user_id", "");
        name = preferences.getString("name", "");


        greetings = (TextView) findViewById(R.id.greetingsMessage);
        right_now = Calendar.getInstance();

        int hour = right_now.get(Calendar.HOUR);
        if(hour >= 5 && hour <=11){
            greetings.setText("Good Morning,\n" + name+"!");
        }else if(hour >=12 && hour <= 19){
            greetings.setText("Good afternoon,\n"+name+"!");
        }
        else{
            greetings.setText("Good evening,\n" +name+"!");
        }

        recyclerView_Popular();


        log_out = (ImageButton) findViewById(R.id.logoutButton);
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

    @Override
    public void onBackPressed() {
        // empty so nothing happens
    }

    public void OnClickCreateNewActivity(View view){
        Intent intent = new Intent(this,CreateActivity.class);
        startActivity(intent);
    }

    public void OnClicklogOut(View view){
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(Homepage.this, LogIn.class);
        startActivity(intent);
    }

    private void recyclerView_Popular() {

        // Creates a horizontal LinearLayoutManager to enable to move between tasks
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView_taskList = findViewById(R.id.recyclerView);
        recyclerView_taskList.setLayoutManager(linearLayoutManager);

        task.add(new Task("Task 1", "Exam", "S11", 10,"Incomplete","Chapter 1, Chapter 2, Chapter 3", "work_process_img"));
        task.add(new Task("Task 2", "Material", "S31", 2,"Incomplete","Chapter 3", "work_process_img"));
        task.add(new Task("Task 3", "Material", "S31", 2,"Incomplete","Chapter 4", "work_process_img"));
        task.add(new Task("Task 4", "Material", "S11", 5,"Incomplete","Chapter 5", "work_process_img"));


        adapter2 = new TasksAdapter(task);
        recyclerView_taskList.setAdapter(adapter2);
    }

}