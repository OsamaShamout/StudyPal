package com.example.studypal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogIn extends AppCompatActivity implements ValidateInformation {

    boolean logged_in = false;

    String[] user_ids = {"1", "2", "3","4"};
    String[] roles = {"2", "2", "1", "1"};
    String[] emails = {"osama@lau.edu","omar@lau.edu", "azzam@lau.edu.lb", "sanaa@lau.edu.lb"};
    String[] names = {"Osama Shamout", "Omar Mlaeb", "Azzam Mourad", "Sanaa Sharafeddine"};
    String[] passwords = {"123123_Osama", "123123_Omar", "123123_Azzam", "123123_Sanaa"};
    EditText input_email;
    EditText input_password;
    TextView dialogue;

    Button log_in;

    int user_id;


    @Override
    public boolean validateEmail(String email) {

        //Ensure a valid email is input.
        Pattern p1 = Pattern.compile("(^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$)");
        Matcher m1 = p1.matcher(email_string);

        if (m1.matches()) {
            email_string = m1.group(1);
            return true;
        } else {
            return false;
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        input_email = (EditText) findViewById(R.id.editTextTextEmailAddressLogIn);
        input_password = (EditText)  findViewById(R.id.editTextPasswordLogIn);
        log_in = (Button) findViewById(R.id.loginButton);
        dialogue = (TextView) findViewById(R.id.alertMessageTextView);

    }

    public void OnClickReturnToWelcome(View view){
        Intent intent = new Intent(this,Welcome.class);
        startActivity(intent);
    }

    public void OnClickGoToRegistration(View view){
        Intent intent = new Intent(this,Registration.class);
        startActivity(intent);
    }

    String email_string;
    String password_string;
    String verification;
    public void OnClickLogIn(View view){
        email_string = input_email.getText().toString();

        //If check e-mail and toast if invalid
        if (!validateEmail(email_string)){
            Toast.makeText(getApplicationContext(), "Please enter a valid email address.", Toast.LENGTH_SHORT).show();
            dialogue.setText("Please enter a valid email address.");
            return;
        }

        password_string = input_password.getText().toString();


        //Check if e-mail exists in database.
      for(int i=0; i<emails.length; i++){

          //Search for email in the data
          //Email found
         if (email_string.equals(emails[i])){
             //Search Password //Password Found
             if (password_string.equals(passwords[i])){
                 Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();

                 logged_in = true;
                 SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(LogIn.this);
                 SharedPreferences.Editor editor = preferences.edit();
                 editor.putString("user_id", user_ids[i]);
                 editor.putString("name",names[i]);
                 editor.putString("role_num", roles[i]);
                 editor.apply();

                 if(logged_in) {
                     //User is an instructor
                     if(roles[i].equalsIgnoreCase("1")){
                         Intent intent = new Intent(LogIn.this, Homepage.class);
                         startActivity(intent);
                         return;

                         //User is a student
                     }else if(roles[i].equalsIgnoreCase("2")){
                         Intent intent = new Intent(LogIn.this, HomepageStudent.class);
                         startActivity(intent);
                         return;
                     }

                 }
             }
             //Password not found
             else{
                 Toast.makeText(getApplicationContext(), "Incorrect Password", Toast.LENGTH_SHORT).show();
                 dialogue.setText("Incorrect Password");
                 return;
             }
             //Email not found
         }else {
             Toast.makeText(getApplicationContext(), "User not registered", Toast.LENGTH_SHORT).show();
             dialogue.setText("User not registered");
             return;
         }
      }

    }
}