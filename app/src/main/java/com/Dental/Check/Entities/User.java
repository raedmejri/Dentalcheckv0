package com.Dental.Check.Entities;

import java.io.Serializable;

public class User implements Serializable {

    private int id;

    private String name;
    private String email;
    private  String password;
    private  int phone;

    private String role;


    public User( String name,  String email, int phone , String role) {

        this.name = name;
        this.email = email;
        this.phone = phone;
        this.role=role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public User(int id, String name, String email, String password, int phone, String role) {
        this.id = id;
        this.name = name;

        this.email = email;
        this.password = password;
        this.phone = phone;

        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phone=" + phone +
                '}';
    }




    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }



    public void setName(String name) {
        this.name = name;
    }





    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public boolean isIs_active() {
        return true;
    }
        public User() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
