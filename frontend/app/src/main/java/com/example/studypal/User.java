package com.example.studypal;

//! needs getFullName method


class User implements UserInterface {
    private String first_name;
    private String last_name;
    private String email;
    private String password;
    private int role_number;

    @Override
    public void checkPrivilege() {
        // assign permissions to users.
    }

    public User(String first_name, String last_name, String email, String password, int role_number) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;
        this.role_number = role_number;
    }

    public String getFullName() {
        return this.first_name + " " + this.last_name;
    }
}
