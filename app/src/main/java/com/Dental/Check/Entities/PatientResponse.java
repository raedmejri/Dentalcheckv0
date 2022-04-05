package com.Dental.Check.Entities;

import java.io.Serializable;

public class PatientResponse implements Serializable {
    private int id;
    private String nom;
    private String lastname;
    private  int phone;

    @Override
    public String toString() {
        return "PatientResponse{" +
                "id=" + id +
                ", name='" + nom + '\'' +
                ", lastname='" + lastname + '\'' +
                ", phone=" + phone +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
