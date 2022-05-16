package com.example.studypal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;

public class Homepage extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {

    ImageButton log_out;
    TextView greetings;
    BottomNavigationView bottomNavigationView;
    String user_id;
    String name;

    ImageView img1;
    ImageView img2;
    ImageView img3;

    //Name Texts
    TextView txt_n1;
    TextView txt_n2;
    TextView txt_n3;

    //Date Texts
    TextView txt_d1;
    TextView txt_d2;
    TextView txt_d3;

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

        Log.e("Name is: ", name);

         txt_n1 = findViewById(R.id.activityTextView1);
         txt_n2  = findViewById(R.id.activityTextView2);
         txt_n3  = findViewById(R.id.activityTextView3);


         txt_d1 = findViewById(R.id.activityDate1);
         txt_d2 = findViewById(R.id.activityDate2);
         txt_d3 = findViewById(R.id.activityDate3);


         Log.e("Hour is ", String.valueOf(hour));


        log_out = (ImageButton) findViewById(R.id.logoutButton);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavMenu);

        bottomNavigationView.setOnItemSelectedListener(this);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch(id){
            case R.id.home:
                Toast.makeText(getApplicationContext(), "You are already in home page.", Toast.LENGTH_SHORT).show();
                break;
            case R.id.favorites:
                Intent intent = new Intent(this,FavoritePage.class);
                startActivity(intent);
                break;
            case R.id.profile:
                Intent intent2 = new Intent(this,Profile.class);
                startActivity(intent2);
                break;
        }

        return false;
    }

    public void OnClickCreateNewActivity(View view){
        Intent intent = new Intent(this,CreateActivity.class);
        startActivity(intent);

    }
//
//    public void OnClickOpenActivity(View view){
//        String tag = view.getTag().toString();
//        Intent intent = new Intent(this,ActivitiesPage.class);
//        intent.putExtra("activity_tag", tag);
//        if(tag.equalsIgnoreCase("txt_n_1") || tag.equalsIgnoreCase("txt_d_1")) {
//            intent.putExtra("activity_id", activity1_id);
//            startActivity(intent);
//        }else if (tag.equalsIgnoreCase("txt_n_2") || tag.equalsIgnoreCase("txt_d_2")) {
//            intent.putExtra("activity_id", activity2_id);
//            startActivity(intent);
//        }else if (tag.equalsIgnoreCase("txt_n_3") || tag.equalsIgnoreCase("txt_d_3")) {
//            intent.putExtra("activity_id", activity3_id);
//            startActivity(intent);
//        }
//
//    }

    public void OnClicklogOut(View view){
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(Homepage.this, LogIn.class);
        startActivity(intent);
    }

}