package com.example.studypal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.HttpsURLConnection;

public class Registration extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String[] user_ids = {"1", "2", "3", "4"};
    String[] roles = {"2", "2", "1", "1"};
    String[] emails = {"osama@lau.edu", "omar@lau.edu", "azzam@lau.edu.lb", "sanaa@lau.edu.lb"};
    String[] names = {"Osama Shamout", "Omar Mlaeb", "Azzam Mourad", "Sanaa Sharafeddine"};
    String[] passwords = {"123123_Osama", "123123_Omar", "123123_Azzam", "123123_Sanaa"};

    EditText first_name;
    EditText last_name;
    EditText email;
    EditText password;
    EditText repeat_password;
    EditText country;

    Button register;

    Spinner spinner_roles;

    Boolean logged_in = false;
    Boolean isInstructor = false;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        first_name = (EditText) findViewById(R.id.firstNameEditText);
        last_name = (EditText) findViewById(R.id.lastNameEditText);
        email = (EditText) findViewById(R.id.editTextEmailAddress);
        password = (EditText) findViewById(R.id.editTextTextPassword);
        repeat_password = (EditText) findViewById(R.id.repeatPasswordEditText);

        register = (Button) findViewById(R.id.registrationButton);

        //Define the spinner of roles
        spinner_roles = findViewById(R.id.listOfRoles);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.list_of_roles, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_roles.setAdapter(adapter);
        spinner_roles.setOnItemSelectedListener(this);

    }


    String role;

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        role = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    int role_number = -1;

    public int getRoleNumber(String role) {
        String[] splitted = role.split(" ");
        int num = -1;
        try {
            num = Integer.parseInt(splitted[1]);
            return num;
        } catch (Exception e) {
            return num;
        }
    }

    public void OnClickReturnToLogIn(View view) {
        Intent intent = new Intent(this, LogIn.class);
        startActivity(intent);
    }


    //Check First Name Method
    public boolean checkFirstName(String first_name) {
        if (first_name.isEmpty()) {
            return false;
        }
        return true;
    }

    //Check Last Name Method
    public boolean checkLastName(String last_name) {
        if (last_name.isEmpty()) {
            return false;
        }
        return true;
    }

    //Check if email entered and if it matches expression
    public boolean checkEmail(String email) {

        //Ensure a valid email is input.
        Pattern p1 = Pattern.compile("(^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$)");
        Matcher m1 = p1.matcher(email);

        if (m1.matches()) {
            email_string = m1.group(1);
            return true;
        } else {
            return false;
        }
    }

    public boolean checkInstructorStatus(String email){
        Pattern p1 = Pattern.compile(".+lb");
        Matcher m1 = p1.matcher(email);

        if (m1.matches()) {
            return true;
        } else {
            return false;
        }

    }
    public boolean checkPassword(String pass) {

        //Ensure a valid password is input.
        Pattern p1 = Pattern.compile("(^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$)");
        Matcher m1 = p1.matcher(pass);

        if (m1.matches()) {
            password_string = m1.group(1);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onBackPressed() {
        // empty so nothing happens
    }

    public boolean checkRepeatPassword(String pass) {

        if (pass.isEmpty()) {
            return false;
        }

        return true;
    }

    //Ensure repeated password is the same as previous password.
    public boolean checkMatchingPassword(String p, String rp) {
        if (p.equals(rp)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkRoleNumber(String r) {
        if (!(getRoleNumber(role) >= 0)) {
            return false;
        } else {
            role_number = getRoleNumber(role);
            return true;
        }
    }

    public boolean checkEmailExists(String email) {
        for (int i = 0; i < emails.length; i++) {
            if (email.equals(emails[i])) {
                return true;
            }
        }
        return false;
    }

    String first_name_string;
    String last_name_string;
    String email_string;
    String password_string;
    String repeat_password_string;

    //Validate information
    public boolean validateInformation() {
        //Gather registration information
        first_name_string = first_name.getText().toString();
        last_name_string = last_name.getText().toString();
        email_string = email.getText().toString();
        password_string = password.getText().toString();
        repeat_password_string = repeat_password.getText().toString();

        //Handle First Name Not Entered
        if (!checkFirstName(first_name_string)) {
            Toast.makeText(getApplicationContext(), "Please enter a first name.", Toast.LENGTH_SHORT).show();
            return false;
        }

        //Handle Last Name Not Entered
        if (!checkLastName(last_name_string)) {
            Toast.makeText(getApplicationContext(), "Please enter a last name.", Toast.LENGTH_SHORT).show();
            return false;
        }


        if (!checkEmail(email_string)) {
            Toast.makeText(getApplicationContext(), "Please enter a valid email address.", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!checkPassword(password_string)) {
            Toast.makeText(getApplicationContext(), "Please enter a valid password.", Toast.LENGTH_SHORT).show();
            return false;
        }

        //Check if repeat password is input.
        if (!checkRepeatPassword(repeat_password_string)) {
            Toast.makeText(getApplicationContext(), "Please enter repeat password.", Toast.LENGTH_SHORT).show();
            return false;
        }


        if (!checkMatchingPassword(password_string, repeat_password_string)) {
            Toast.makeText(getApplicationContext(), "Passwords do not match.", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!checkRoleNumber(role)) {
            Toast.makeText(getApplicationContext(), "Please assign a valid role.", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (checkEmailExists(email_string)) {
            Toast.makeText(getApplicationContext(), "User already exists.", Toast.LENGTH_SHORT).show();
            return false;
        }
        //Inforamtion Valid
        return true;
    }

    ArrayList<Course> courses = new ArrayList<Course>();
    ArrayList<Task> tasks = new ArrayList<Task>();

    public void OnClickRegister(View view) {
        //Check Information
        if (validateInformation()) {

            //Information is valid
            isInstructor = checkInstructorStatus(email_string);
            if (isInstructor) {
                Instructor instr = new Instructor(first_name_string, last_name_string, email_string, password_string, courses, tasks, "Arts and Sciences");
            } else if (!isInstructor) {
                Student student = new Student(first_name_string, last_name_string, email_string, password_string);
            }

            Toast.makeText(getApplicationContext(), "Successfully registered", Toast.LENGTH_SHORT).show();

            logged_in = true;
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(Registration.this);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("email", String.valueOf(email));
            editor.putString("role_num", String.valueOf(role_number));
            editor.apply();

            if (logged_in) {
                //User is an instructor
                if (Integer.toString(role_number).equalsIgnoreCase("1")) {
                    Intent intent = new Intent(Registration.this, Homepage.class);
                    startActivity(intent);
                    return;
                    //User is a student
                } else if (Integer.toString(role_number).equalsIgnoreCase("2")) {
                    Intent intent = new Intent(Registration.this, HomepageStudent.class);
                    startActivity(intent);
                    return;
                }
            }

        }
    }
}