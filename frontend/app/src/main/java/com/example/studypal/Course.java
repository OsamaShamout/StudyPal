package com.example.studypal;

import java.util.ArrayList;
import java.util.Date;

public class Course {
    private String name;
    private Date start_date;
    private Date end_date;
    private String instructor;
    private ArrayList<Material> materials;
    private ArrayList<Section> sections;

    public Course(String name, Date start_date, Date end_date, String instructor, ArrayList<Material> materials) {
        this.name = name;
        this.start_date = start_date;
        this.end_date = end_date;
        this.instructor = instructor;
        this.materials = materials;
        sections.add(new Section("section0", name, instructor, null));
    }

    public void modifyCourse(String name, Date start_date, Date end_date) {
        this.name = name;
        this.start_date = start_date;
        this.end_date = end_date;
    }
}
