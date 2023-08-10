package com.example.studypal;

import java.io.Serializable;
import java.util.ArrayList;

//! Task needs a getStatus and setProgress methods
public class Task implements Serializable {
    private String name;
    private String type;
    private String section;
    private String img;
    private int progress; // represented as 0-100%
    private float study_hours;
    private String status;
    private String materials;

    public Task(String name, String type, String section, float study_hours, String status,
                String materials, String img) {
        this.name = name;
        this.type = type;
        this.section = section;
        this.study_hours = study_hours;
        this.status = status;
        this.materials = materials;
        this.img = img;
        // this.timer = new Timer(0,0,0)
        this.progress = 0;
    }

<<<<<<< HEAD
=======
<<<<<<< HEAD
    public Task(String name, String type, String section, String img, int progress, int study_hours, String status, ArrayList<Material> materials) {
        this.name = name;
        this.type = type;
        this.section = section;
        this.img = img;
        this.progress = progress;
        this.study_hours = study_hours;
        this.status = status;
        this.materials = materials;
    }

=======
>>>>>>> c42b3703457acf1a776de5f77afd684793c7a6fe
    public Task(String name, String img) {
        this.name = name;
        this.img = img;
    }
>>>>>>> f13369f8807fb4046b1399edb8c3ba0625d953c2

<<<<<<< HEAD
    public String getImg() {
        return img;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getSection() {
        return section;
    }

    public int getProgress() {
        return progress;
    }

    public int getStudy_hours() {
        return study_hours;
    }

    public ArrayList<Material> getMaterials() {
        return materials;
    }

=======
>>>>>>> c42b3703457acf1a776de5f77afd684793c7a6fe
    public void viewTask() {
        System.out.println("Task name: " + this.name);
        System.out.println("Task type: " + this.type);
        System.out.println("Task section: " + this.section);
        System.out.println("Task study hours: " + this.study_hours);

    }

    public void updateRecords() {

    }

    public void sendTask() {

    }

    public void updateTask(String name, String type, String section, float study_hours, String status,
                           String materials) {
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

    public float getStudy_hours() {
        return study_hours;
    }

    public void setStudy_hours(float study_hours) {
        this.study_hours = study_hours;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMaterials() {
        return materials;
    }

    public void setMaterials(String materials) {
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

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", section='" + section + '\'' +
                ", img='" + img + '\'' +
                ", progress=" + progress +
                ", study_hours=" + study_hours +
                ", status='" + status + '\'' +
                ", materials='" + materials + '\'' +
                '}';
    }
}
