package com.Dental.Check.Entities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Event {
    int  idc;
    String nom, lastname, time, date,phone;




    public int getIdc() {
        return idc;
    }

    public Event() {
    }

    public Event(int idc, String nom, String lastname, String time, String date,  String phone) {
        this.idc = idc;
        this.nom = nom;
        this.lastname = lastname;
        this.time = time;
        this.date = date;

        this.phone = phone;
    }

    public Event( String time, String date) {

        this.time = time;
        this.date = date;

    }

    public Event(String nom, String lastname, String phone,String time, String date ) {
        this.nom = nom;
        this.lastname = lastname;
        this.time = time;
        this.date = date;

        this.phone = phone;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        date = date;
    }



    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

