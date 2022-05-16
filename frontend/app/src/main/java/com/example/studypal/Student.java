package com.example.studypal;

import java.util.ArrayList;
import java.util.Date;

public class Student extends User {
    private ArrayList<Task> tasks_assigned;

    public Student(String first_name, String last_name,String email, String password) {
        super(first_name, last_name, email, password, 2);
    }

    public void viewAllTasks() {
        for (Task task : tasks_assigned) {
            task.viewTask();
        }
    }

    public void viewIncompleteTasks() {
        for (Task task : tasks_assigned) {
            if (task.getStatus() == "incomplete")
                task.viewTask();
        }
    }

    public void viewInProgressTasks() {
        for (Task task : tasks_assigned) {
            if (task.getStatus() == "inprogress")
                task.viewTask();
        }
    }

    public void viewCompleteTasks() {
        for (Task task : tasks_assigned) {
            if (task.getStatus() == "complete")
                task.viewTask();
        }
    }

}
