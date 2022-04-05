package com.Dental.Check.Entities;

import java.io.Serializable;

public class Patient implements Serializable {
    private int id;
    private String nom;
    private String lastname;
    private  int phone;

    public Patient() {
    }

    public Patient(int id, String name, String lastname, int phone) {
        this.id = id;
        this.nom = name;
        this.lastname = lastname;
        this.phone = phone;
    }

    public Patient(String name, String lastname, int phone) {
        this.nom = name;
        this.lastname = lastname;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }



    public String getName() {
        return nom;
    }

    public void setName(String name) {
        this.nom = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Patient{" +
                ", name='" + nom + '\'' +
                ", lastname='" + lastname + '\'' +
                ", phone=" + phone +
                '}';
    }

}
