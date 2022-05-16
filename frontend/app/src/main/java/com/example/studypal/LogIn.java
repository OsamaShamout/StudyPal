package com.example.studypal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class LogIn extends AppCompatActivity implements ValidateInformation {
    private FirebaseAuth mAuth;

    String[] user_ids = {"1", "2", "3","4"};
    String[] roles = {"2", "2", "1", "1"};
    String[] emails = {"osama@lau.edu","omar@lau.edu", "bilal@lau.edu", "sameer@lau.edu"};
    String[] names = {"Osama Shamout", "Omar Mlaeb", "Bilal Hamdanieh", "Sameer Al Jabari "};
    String[] passwords = {"123123_Osama", "123123_Omar", "123123_Bilal", "123123_Sameer"};
    EditText input_email;
    EditText input_password;
    TextView dialogue;

    Button log_in;

    int user_id;

    boolean isInstructor = false;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        input_email = (EditText) findViewById(R.id.editTextTextEmailAddressLogIn);
        input_password = (EditText)  findViewById(R.id.editTextPasswordLogIn);
        log_in = (Button) findViewById(R.id.loginButton);
        dialogue = (TextView) findViewById(R.id.alertMessageTextView);

        mAuth = FirebaseAuth.getInstance();

    }

    public void OnClickReturnToWelcome(View view){
        Intent intent = new Intent(this,Welcome.class);
        startActivity(intent);
    }

    public void OnClickGoToRegistration(View view){
        Intent intent = new Intent(this,Registration.class);
        startActivity(intent);
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

        if (pass.isEmpty()) {
            return false;
        }

        return true;
    }

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

    //Change UI according to user data.
    public void updateUI(FirebaseUser account) {

        if (account != null) {
            Toast.makeText(this, "You have signed in", Toast.LENGTH_LONG).show();
            //User is an instructor
            if(isInstructor) {
                Intent intent = new Intent(LogIn.this, Homepage.class);
                startActivity(intent);
            }
            //User is a student
            } else if (!isInstructor) {
                    Intent intent = new Intent(LogIn.this, HomepageStudent.class);
                    startActivity(intent);
            }
        else{
            Toast.makeText(this, "You're not signed in", Toast.LENGTH_LONG).show();
        }

    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    String email_string;
    String password_string;

    int index;
    public void OnClickLogIn(View view){

        email_string = input_email.getText().toString();

        //If check e-mail and toast if invalid
        if (!validateEmail(email_string)){
            Toast.makeText(getApplicationContext(), "Please enter a valid email address.", Toast.LENGTH_SHORT).show();
            dialogue.setText("Please enter a valid email address.");
            return;
        }

        password_string = input_password.getText().toString();

        if(!checkPassword(password_string)){
            Toast.makeText(getApplicationContext(), "Please enter a password.", Toast.LENGTH_SHORT).show();
            dialogue.setText("Please enter a password.");
            return;
        }
        isInstructor = checkInstructorStatus(email_string);

        mAuth.signInWithEmailAndPassword(email_string, password_string)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull com.google.android.gms.tasks.Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG", "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(LogIn.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
    }
}