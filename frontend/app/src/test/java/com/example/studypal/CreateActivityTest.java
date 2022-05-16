package com.example.studypal;

import static org.junit.Assert.*;

import org.junit.Test;

public class CreateActivityTest {

    @Test
    public void onClickReturnHomepage() {
    }

    @Test
    public void checkTaskName() {
        CreateActivity createActivity = new CreateActivity();
        boolean result = createActivity.checkTaskName("Hello");

        assertEquals("true", result);

    }

    @Test
    public void checkTaskDescription() {
    }

    @Test
    public void checkMaterials() {
    }

    @Test
    public void checkType() {
    }

    @Test
    public void checkSection() {
    }

    @Test
    public void verifyStudyHours() {
    }

    @Test
    public void onClickCreateTask() {
    }
}