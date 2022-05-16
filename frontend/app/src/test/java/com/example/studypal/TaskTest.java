package com.example.studypal;

import static org.junit.Assert.*;

import org.junit.Test;

public class TaskTest {

    @Test
    public void getName() {
        Task t1 = new Task("Task 1", "Exam", "S11", 4,"Incomplete","Chapter 1", "No image");
        String name = t1.getName();

        assertEquals("Task 1", name);
    }

    @Test
    public void setName() {
    }
}