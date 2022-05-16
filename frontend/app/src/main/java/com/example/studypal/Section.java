package com.example.studypal;

import java.util.*;

//! add students and getInstructor and enroll method
public class Section {
    private String name;
    private String course_name;
    private String instructor;
    private ArrayList<Task> tasks_given;
    private ArrayList<Student> students_enrolled;

    public Section(String name, String course_name, String instructor, ArrayList<Task> tasks_given) {
        this.name = name;
        this.course_name = course_name;
        this.instructor = instructor;
        this.tasks_given = tasks_given;
    }

    public void enroll(Student student) {
        students_enrolled.add(student);
    }

    public String getInstructor() {
        return instructor;
    }
}
