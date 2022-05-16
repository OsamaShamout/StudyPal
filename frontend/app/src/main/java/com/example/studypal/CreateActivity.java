package com.example.studypal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    Spinner spinner_list_types;
    Spinner spinner_list_sections;

    EditText task_name;
    EditText task_description;

    EditText time_study;

    String user_id;

    float study_hours;

    String task_name_string;
    String task_description_string;

    // initialize variables
    TextView textView;
    boolean[] selectedMaterial;
    ArrayList<Integer> materialList = new ArrayList<>();

    String[] materialArray = {"Chapter 1", "Chapter 2", "Chapter 3", "Chapter 4", "Chapter 5", "Chapter 6"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        task_name = (EditText) findViewById(R.id.editTextTaskName);
        task_description = (EditText) findViewById(R.id.editTextTaskDescription);

        //Define the spinner of types
        spinner_list_types= (Spinner) findViewById(R.id.listofTaskTypes);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.list_types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_list_types.setAdapter(adapter);
        spinner_list_types.setOnItemSelectedListener(this);
        spinner_list_types.setPrompt("Set Task Type:");

        //Define the spinner of sections
        spinner_list_sections= (Spinner) findViewById(R.id.listofSections);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.list_sections, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_list_sections.setAdapter(adapter2);
        spinner_list_sections.setOnItemSelectedListener(this);
        spinner_list_sections.setPrompt("Set Task Section:");

        time_study = (EditText) findViewById(R.id.timeStudyEditText);

        // assign variable
        textView = findViewById(R.id.txtName);

        selectedMaterial = new boolean[materialArray.length];

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Initialize alert dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(CreateActivity.this);

                // set title
                builder.setTitle("Select Material");

                // set dialog non cancelable
                builder.setCancelable(false);

                builder.setMultiChoiceItems(materialArray, selectedMaterial, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        // check condition
                        if (b) {
                            // when checkbox selected
                            // Add position  in lang list
                            materialList.add(i);
                            // Sort array list
                            Collections.sort(materialList);
                        } else {
                            // when checkbox unselected
                            // Remove position from langList
                            materialList.remove(Integer.valueOf(i));
                        }
                    }
                });

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Initialize string builder
                        StringBuilder stringBuilder = new StringBuilder();
                        // use for loop
                        for (int j = 0; j < materialList.size(); j++) {
                            // concat array value
                            stringBuilder.append(materialArray[materialList.get(j)]);
                            // check condition
                            if (j != materialList.size() - 1) {
                                // When j value  not equal
                                // to lang list size - 1
                                // add comma
                                stringBuilder.append(", ");
                            }
                        }
                        // set text on textView
                        textView.setText(stringBuilder.toString());
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // dismiss dialog
                        dialogInterface.dismiss();
                    }
                });
                builder.setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // use for loop
                        for (int j = 0; j < selectedMaterial.length; j++) {
                            // remove all selection
                            selectedMaterial[j] = false;
                            // clear language list
                            materialList.clear();
                            // clear text view value
                            textView.setText("");
                        }
                    }
                });
                // show dialog
                builder.show();
            }
        });

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        user_id = preferences.getString("user_id", "");

    }
//
    String text;
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
         text = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void OnClickReturnHomepage(View view){
        Intent intent = new Intent(this,Homepage.class);
        startActivity(intent);
    }

   public boolean checkTaskName(String name){
       //Ensure a task name is given.
       Pattern p1 = Pattern.compile("^(\\s?)+((.)+)");
       Matcher m1 = p1.matcher(name);

       if(m1.matches()) {
           task_name_string = m1.group(2);
           return true;
       }else{
           return false;
       }

   }

    public boolean checkTaskDescription(String description){
        //Ensure a task name is given.
        Pattern p1 = Pattern.compile("^(\\s?)+((.)+)");
        Matcher m1 = p1.matcher(description);

        if(m1.matches()) {
            task_description_string = m1.group(2);
            return true;
        }else{
            return false;
        }
    }

    public boolean checkMaterials(String materials){
        if(materials.isEmpty() || materials.equals("") || materials.equalsIgnoreCase("Set Material")){
            return false;
        }
        return true;
    }

    public boolean checkType(String type){
        if(type.isEmpty() || type.equals("") || type.equalsIgnoreCase("Choose Type")){
            return false;
        }
        return true;
    }

    public boolean checkSection(String section){
        if(section.isEmpty() || section.equals("") || section.equalsIgnoreCase("Choose Section(s)")){
            return false;
        }
        return true;
    }

    public boolean verifyStudyHours(String hours){
        try{
            study_hours = Float.parseFloat(time_study.getText().toString());
        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(), "Invalid Data Input", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!(study_hours >0)){
            return false;
        }
        return true;
    }

    String task_type;
    String section;
    String materials;
    public void onClickCreateTask(View view){

        //Obtain all information to register activity
        task_name_string = task_name.getText().toString();
        task_description_string = task_description.getText().toString();

        //                              Validate Data Is Correct
        //                              -------------------------

        boolean name_check = checkTaskName(task_name_string);

        if(!name_check){
            Toast.makeText(getApplicationContext(), "Please enter a valid task name", Toast.LENGTH_SHORT).show();
            return;
        }

        boolean description_check = checkTaskDescription(task_description_string);
        Log.e("return", String.valueOf(description_check));
        if(!description_check){
            Toast.makeText(getApplicationContext(), "Please enter a valid task description", Toast.LENGTH_SHORT).show();
            return;
        }


        //Obtain Task Type
        AdapterView<?> parent = spinner_list_types;
        int number = spinner_list_types.getSelectedItemPosition();
        task_type = spinner_list_types.getItemAtPosition(number).toString();

        if(!checkType(task_type)){
            Toast.makeText(getApplicationContext(), "Invalid Task Type Input", Toast.LENGTH_SHORT).show();
            return;
        }

        //Obtain Task Section
        AdapterView<?> parent2 = spinner_list_sections;
        int number2 = spinner_list_sections.getSelectedItemPosition();
        section = spinner_list_sections.getItemAtPosition(number2).toString();

        if(!checkSection(section)){
            Toast.makeText(getApplicationContext(), "Invalid Section Input", Toast.LENGTH_SHORT).show();
            return;
        }


        materials = textView.getText().toString();
        Log.e("Material:", materials);

        if(!checkMaterials(materials)){
            Toast.makeText(getApplicationContext(), "Invalid Material Input", Toast.LENGTH_SHORT).show();
            return;
        }


        //Obtain Study Hours Section and check condition
        if(!(verifyStudyHours(time_study.getText().toString()))){
            //If study hours less than 0
            Toast.makeText(getApplicationContext(), "Hours must be greater than 0.", Toast.LENGTH_SHORT).show();
            return;
        }

        Homepage page = new Homepage();
        HomepageStudent page2 = new HomepageStudent();

        Task task1 = new Task(task_name_string,task_type, section, study_hours, "Incomplete", materials, "work_process_img");

        Log.e("Task added is:", task1.toString());
        page.getTask().add(task1);
        page2.getTask().add(task1);


        Toast.makeText(getApplicationContext(), "Task Added Successfully", Toast.LENGTH_SHORT).show();


    }
}