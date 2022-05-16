package com.example.studypal;

import java.util.ArrayList;

//! Task needs a getStatus and setProgress methods
public class Task  {
    private String name;
    private String type;
    private String section;
    private String img;
    private int progress; // represented as 0-100%
    private int study_hours;
    private String status;
    private ArrayList<Material> materials;

    public Task(String name, String type, String section, int study_hours, String status,
                ArrayList<Material> materials) {
        this.name = name;
        this.type = type;
        this.section = section;
        this.study_hours = study_hours;
        this.status = status;
        this.materials = materials;

        // this.timer = new Timer(0,0,0)
        this.progress = 0;
    }

    public Task(String name, String img) {
        this.name = name;
        this.img = img;
    }

    public void viewTask() {
        System.out.println("Task name: " + this.name);
        System.out.println("Task type: " + this.type);
        System.out.println("Task section: " + this.section);
        System.out.println("Task study hours: " + this.study_hours);
        for (Material material : materials) {
            material.getMaterial();
        }
    }

    public void updateRecords() {

    }

    public void sendTask() {

    }

    public void updateTask(String name, String type, String section, int study_hours, String status,
                           ArrayList<Material> materials) {
        this.name = name;
        this.type = type;
        this.section = section;
        this.study_hours = study_hours;
        this.status = status;
        this.materials = materials;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public int getStudy_hours() {
        return study_hours;
    }

    public void setStudy_hours(int study_hours) {
        this.study_hours = study_hours;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<Material> getMaterials() {
        return materials;
    }

    public void setMaterials(ArrayList<Material> materials) {
        this.materials = materials;
    }

    public void deleteTask() {
        // remove task from all tasks arrays
    }

    public String getStatus() {
        return status;
    }

    public void SetProgress(int progress) {
        this.progress = progress;
    }

    public boolean verifyStudyHours() {
        if (study_hours > 0) {
            return true;
        }
        return false;
    }
}
