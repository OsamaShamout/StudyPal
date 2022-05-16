package com.example.studypal;

public class Material {
    private String name;

    public Material(String name, String type) {
        this.name = name;

    }

    public void getMaterial() {
        System.out.println("Material name: " + this.name);
    }
}
