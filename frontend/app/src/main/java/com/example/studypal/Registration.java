package com.example.studypal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.HttpsURLConnection;

public class Registration extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText first_name;
    EditText last_name;
    EditText email;
    EditText password;
    EditText repeat_password;
    EditText country;

    Button register;

    Spinner spinner_roles;

    Boolean logged_in = false;

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


    public void createDialog(){
        AlertDialog password_alert = new AlertDialog.Builder(Registration.this).create();
        password_alert.setTitle("Password has to have:");
        password_alert.setMessage("1. Minimum eight characters\n2. At least one uppercase letter\n3. One lowercase lette\n4. One number\n5. One special character\n6. Maximum 30 characters");
        password_alert.setButton(AlertDialog.BUTTON_POSITIVE, "Done", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        password_alert.show();
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
    public int getRoleNumber(String role){
        String[] splitted = role.split(" ");
        int num = -1;
        try{
            num = Integer.parseInt(splitted[1]);
            return num;
        }
        catch (Exception e){
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
        if(!(getRoleNumber(role) >= 0)){
            return false;
        }else{
            role_number = getRoleNumber(role);
            return true;
        }
    }

    //Validate information
    public boolean validateInformation(){
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
            createDialog();
            return false;
        }

        //Check if repeat password is input.
        if(!checkRepeatPassword(repeat_password_string)){
            Toast.makeText(getApplicationContext(), "Please enter repeat password.", Toast.LENGTH_SHORT).show();
            return false;
        }


        if(!checkMatchingPassword(password_string, repeat_password_string)){
            Toast.makeText(getApplicationContext(), "Passwords do not match.", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(!checkRoleNumber(role)){
            Toast.makeText(getApplicationContext(), "Please assign a valid role.", Toast.LENGTH_SHORT).show();
            return false;
        }
        //Inforamtion Valid
        return true;
    }

    String first_name_string;
    String last_name_string;
    String email_string;
    String password_string;
    String repeat_password_string;

    public void OnClickRegister(View view) {
        //Gather registration information
        first_name_string = first_name.getText().toString();
        last_name_string = last_name.getText().toString();
        email_string = email.getText().toString();
        password_string = password.getText().toString();
        repeat_password_string = repeat_password.getText().toString();


        if (validateInformation()){
            SendSignUptoDB sign_up = new SendSignUptoDB();
            String url2 = "https://mcprojs.000webhostapp.com/backend_se/sign_up.php";
            sign_up.execute(url2);
        }

    }

    //                  ---------------------------------------------------
    //                                      APIs Log In
    //                  ---------------------------------------------------

    String verification;
    //Register User API
    public class SendSignUptoDB extends AsyncTask<String, Void, String> {
        protected String doInBackground(String... urls) {

            //Variables to initiate connection.
            URL url;
            HttpsURLConnection conn;

            try {
                //Establishing connection between application and API.
                url = new URL(urls[0]);
                conn = (HttpsURLConnection) url.openConnection();
                conn.setDoOutput(true);
                conn.setDoInput(true);
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Accept-Charset", "UTF-8");

                conn.setReadTimeout(10000);
                conn.setConnectTimeout(15000);

                conn.connect();

                //Creating new JSON object to communicate with it to DB.
                JSONObject jo = new JSONObject();
                StringBuffer packedData = new StringBuffer();

                //Send the variables to their respective $_POST.
                jo.put("first_name", first_name_string);
                jo.put("last_name", last_name_string);
                jo.put("email", email_string);
                jo.put("password", password_string);
                jo.put("role_number", role_number);

                //Pack data to be processed by PHP for $_POST.
                boolean firstValue = true;

                Iterator it = jo.keys();

                do {
                    String key = it.next().toString();
                    String value = jo.get(key).toString();

                    if (firstValue) {
                        firstValue = false;
                    } else {
                        packedData.append("&");
                    }

                    packedData.append(URLEncoder.encode(key, "UTF-8"));
                    packedData.append("=");
                    packedData.append(URLEncoder.encode(value, "UTF-8"));

                } while (it.hasNext());

                //Log in console to track. "e" used as color red will appear more significantly while reading log.
                Log.e("Packed data:", packedData.toString());

                //Write to PHP file.
                OutputStream os = conn.getOutputStream();
                BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(os, StandardCharsets.UTF_8));

                wr.write(packedData.toString());
                wr.flush();
                wr.close();

                //InputStreams to obtain input from API..

                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder total = new StringBuilder();
                String line;
                int serverResponseCode = conn.getResponseCode();
                String serverResponseMessage = conn.getResponseMessage();
                while ((line = in.readLine()) != null) {
                    total.append(line).append('\n');
                }
                Log.e("Tag", "Server Response is:" + total.toString() + ": " + serverResponseMessage + "\nResponse Code is: " + serverResponseCode);

                verification = total.toString();

                //Log server return.
                Log.e("test", "result from server: " + verification);

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return verification;

        }


        protected void onPostExecute(String s){
            super.onPostExecute(s);
            try {
                Log.e("TAG POST:",s);
                if(s.equalsIgnoreCase("User already exist\n")){
                    Toast.makeText(getApplicationContext(), "User already exists", Toast.LENGTH_SHORT).show();

                } else {
                    String[] split_values = s.split("_");
                    Log.e("Split return", split_values[0]);
                    Log.e("Split user_id", split_values[1]);
                    Log.e("Split name", split_values[2]);
                    Log.e("Split role_number", split_values[3]);

                    String returned_statement = split_values[0];
                    if(returned_statement.equalsIgnoreCase("Success")) {
                        Toast.makeText(getApplicationContext(), "Successfully registered", Toast.LENGTH_SHORT).show();

                        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(Registration.this);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("user_id", split_values[1]);
                        editor.putString("name",split_values[2]);
                        editor.putString("role_number",split_values[2]);
                        editor.apply();

                        logged_in = true;

                        editor.putBoolean("status", logged_in);

                        //Validate Information from DB
                        if (logged_in) {
                            Intent intent = new Intent(Registration.this, Homepage.class);
                            startActivity(intent);
                        }
                    }
                }



            }
            catch (Exception e){
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "Error in receiving data.", Toast.LENGTH_LONG).show();
            }

        }
    }
}