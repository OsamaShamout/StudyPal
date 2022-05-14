package com.example.studypal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Welcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //To make all elements fade in
        com.airbnb.lottie.LottieAnimationView animation = findViewById(R.id.welcomeAnim);
        TextView welcome_text = (TextView) findViewById(R.id.welcomeText);
        Button start_button = (Button) findViewById(R.id.getStarted);

       // animation.setX(-1000);
      //  animation.animate().translationXBy(1000).rotation(3600).setDuration(1000);


    }

    public void OnClickGetStarted(View view){
        Intent intent = new Intent(this,LogIn.class);
        startActivity(intent);
    }
}