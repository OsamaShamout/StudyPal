package com.example.studypal;

import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Date;

public class Instructor extends User {
    private ArrayList<Course> courses;
    private ArrayList<Task> tasks_given;
    private String department;

    public Instructor(String first_name, String last_name,String email, String password, ArrayList<Course> courses, ArrayList<Task> tasks_given, String department) {
        super(first_name, last_name, email, password, 1);
        this.courses = courses;
        this.tasks_given = tasks_given;
        this.department = department;
    }

    public void addTask(String name, String type, String section, int study_hours, String status,
                        String materials, String img) {
        Task task = new Task(name, type, section, study_hours, "Incomplete", materials, img);
    }

    public void deleteTask(String task_name) {
        // search for task with name task_name and remove it
    }

    public boolean hasSections(ArrayList<Section> sections) {
        for (Section section : sections) {
            if (section.getInstructor() == this.getFullName()) {
                return true;
            }
        }
        return false;
    }
}
