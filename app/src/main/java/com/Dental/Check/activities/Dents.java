package com.Dental.Check.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.Dental.Check.Controller.patientDetails;
import com.Dental.Check.Entities.Patient;
import com.Dental.Check.R;

public class Dents extends AppCompatActivity {
Button incisive_centrale_haute_gauche_haut ,incisive_centrale_haute_droit_haut,incisive_laterale_droit_haut;
Button canine_droit_haut,premiere_premolaire_droit_haut,deusieme_premolaire_droit_haut,premiere_molaire_droit_haut,deusieme_molaire_droit_haut ;
Button deusieme_molaire_gauche_haut,premiere_molaire_gauche_haut,deusieme_premolaire_gauche_haut,premiere_premolaire_gauche_haut;
Button canine_gauche_haut,incisive_laterale_gauche_haut,troisieme_molaire_gauche_haut,troisieme_molaire_droit_haut,troisieme_molaire_gauche_bas;
Button deusieme_molaire_gauche_bas,premiere_molaire_gauche_bas,deusieme_premolaire_gauche_bas,premiere_premolaire_gauche_bas,canine_gauche_bas;
Button incisive_laterale_gauche_bas,incisive_centrale_gauche_bas,incisive_centrale_droit_bas,incisive_laterale_droit_bas,canine_droit_bas;
Button premier_premolaire_droit_bas,deusieme_premolaire_droit_bas,premiere_molaire_droit_bas,desieme_molaire_droit_bas,troisieme_molaire_droit_bas;
    private SharedPreferences sharedPreferences;
    Patient patient;
    String named;
    String lastt;
    String p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dents);
        troisieme_molaire_droit_bas = findViewById(R.id.troisieme_molaire_droit_bas);
        troisieme_molaire_droit_bas.setOnClickListener(v -> saveButtomId("troisieme_molaire_droit_bas",named,lastt,p));
        desieme_molaire_droit_bas =findViewById(R.id.desieme_molaire_droit_bas);
        desieme_molaire_droit_bas.setOnClickListener(v -> saveButtomId("desieme_molaire_droit_bas",named,lastt,p));
        premiere_molaire_droit_bas = findViewById(R.id.premiere_molaire_droit_bas);
        premiere_molaire_droit_bas.setOnClickListener(v -> saveButtomId("premiere_molaire_droit_bas",named,lastt,p));
        deusieme_premolaire_droit_bas = findViewById(R.id.deusieme_premolaire_droit_bas);
        deusieme_premolaire_droit_bas.setOnClickListener(v -> saveButtomId("deusieme_premolaire_droit_bas",named,lastt,p));
        premier_premolaire_droit_bas = findViewById(R.id.premier_premolaire_droit_bas);
        premier_premolaire_droit_bas.setOnClickListener(v -> saveButtomId("premier_premolaire_droit_bas",named,lastt,p));
        canine_droit_bas = findViewById(R.id.canine_droit_bas);
        canine_droit_bas.setOnClickListener(v -> saveButtomId("canine_droit_bas",named,lastt,p));
        incisive_laterale_droit_bas=findViewById(R.id.incisive_laterale_droit_bas);
        incisive_laterale_droit_bas.setOnClickListener(v -> saveButtomId("incisive_laterale_droit_bas",named,lastt,p));
        incisive_centrale_droit_bas= findViewById(R.id.incisive_centrale_droit_bas);
        incisive_centrale_droit_bas.setOnClickListener(v -> saveButtomId("incisive_centrale_droit_bas",named,lastt,p));
        incisive_centrale_gauche_bas=findViewById(R.id.incisive_centrale_gauche_bas);
        incisive_centrale_gauche_bas.setOnClickListener(v -> saveButtomId("incisive_centrale_gauche_bas",named,lastt,p));
        incisive_laterale_gauche_bas= findViewById(R.id.incisive_laterale_gauche_bas);
        incisive_laterale_gauche_bas.setOnClickListener(v -> saveButtomId("incisive_laterale_gauche_bas",named,lastt,p));
        canine_gauche_bas=findViewById(R.id.canine_gauche_bas);
        canine_gauche_bas.setOnClickListener(v -> saveButtomId("canine_gauche_bas",named,lastt,p));
        premiere_premolaire_gauche_bas = findViewById(R.id.premiere_premolaire_gauche_bas);
        premiere_premolaire_gauche_bas.setOnClickListener(v -> saveButtomId("premiere_premolaire_gauche_bas",named,lastt,p));
        deusieme_premolaire_gauche_bas = findViewById(R.id.deusieme_premolaire_gauche_bas);
        deusieme_premolaire_gauche_bas.setOnClickListener(v -> saveButtomId("deusieme_premolaire_gauche_bas",named,lastt,p));
        premiere_molaire_gauche_bas = findViewById(R.id.premiere_molaire_gauche_bas);
        premiere_molaire_gauche_bas.setOnClickListener(v -> saveButtomId("premiere_molaire_gauche_bas",named,lastt,p));
        deusieme_molaire_gauche_bas=findViewById(R.id.deusieme_molaire_gauche_bas);
        deusieme_molaire_gauche_bas.setOnClickListener(v -> saveButtomId("deusieme_molaire_gauche_bas",named,lastt,p));
        troisieme_molaire_gauche_bas= findViewById(R.id.troisieme_molaire_gauche_bas);
        troisieme_molaire_gauche_bas.setOnClickListener(v -> saveButtomId("troisieme_molaire_gauche_bas",named,lastt,p));
        troisieme_molaire_droit_haut =findViewById(R.id.troisieme_molaire_droit_haut);
        troisieme_molaire_droit_haut.setOnClickListener(v -> saveButtomId("troisieme_molaire_droit_haut",named,lastt,p));
        troisieme_molaire_gauche_haut = findViewById(R.id.troisieme_molaire_gauche_haut);
        troisieme_molaire_gauche_haut.setOnClickListener(v -> saveButtomId("troisieme_molaire_gauche_haut",named,lastt,p));
        incisive_laterale_gauche_haut = findViewById(R.id.incisive_laterale_gauche_haut);
        incisive_laterale_gauche_haut.setOnClickListener(v -> saveButtomId("incisive_laterale_gauche_haut",named,lastt,p));
        canine_gauche_haut =findViewById(R.id.canine_gauche_haut);
        canine_gauche_haut.setOnClickListener(v -> saveButtomId("canine_gauche_haut",named,lastt,p));
        premiere_premolaire_gauche_haut=findViewById(R.id.premiere_premolaire_gauche_haut);
        premiere_premolaire_gauche_haut.setOnClickListener(v -> saveButtomId("premiere_premolaire_gauche_haut",named,lastt,p));
        deusieme_premolaire_gauche_haut=findViewById(R.id.deusieme_premolaire_gauche_haut);
        deusieme_premolaire_gauche_haut.setOnClickListener(v -> saveButtomId("deusieme_premolaire_gauche_haut",named,lastt,p));
        premiere_molaire_gauche_haut= findViewById(R.id.premiere_molaire_gauche_haut);
        premiere_molaire_gauche_haut.setOnClickListener(v -> saveButtomId("premiere_molaire_gauche_haut",named,lastt,p));
        deusieme_molaire_gauche_haut=findViewById(R.id.deusieme_molaire_gauche_haut);
        deusieme_molaire_gauche_haut.setOnClickListener(v -> saveButtomId("deusieme_molaire_gauche_haut",named,lastt,p));
        deusieme_molaire_droit_haut=findViewById(R.id.deusieme_molaire_droit_haut);
        deusieme_molaire_droit_haut.setOnClickListener(v -> saveButtomId("deusieme_molaire_droit_haut",named,lastt,p));
        premiere_molaire_droit_haut=findViewById(R.id.premiere_molaire_droit_haut);
        premiere_molaire_droit_haut.setOnClickListener(v -> saveButtomId("premiere_molaire_droit_haut",named,lastt,p));
        deusieme_premolaire_droit_haut= findViewById(R.id.deusieme_premolaire_droit_haut);
        deusieme_premolaire_droit_haut.setOnClickListener(v -> saveButtomId("deusieme_premolaire_droit_haut",named,lastt,p));
        premiere_premolaire_droit_haut = findViewById(R.id.premiere_premolaire_droit_haut);
        premiere_premolaire_droit_haut.setOnClickListener(v -> saveButtomId("premiere_premolaire_droit_haut",named,lastt,p));
        canine_droit_haut = findViewById(R.id.canine_droit_haut);
        canine_droit_haut.setOnClickListener(v -> saveButtomId("canine_droit_haut",named,lastt,p));
        incisive_laterale_droit_haut = findViewById(R.id.incisive_laterale_droit_haut);
        incisive_laterale_droit_haut.setOnClickListener(v -> saveButtomId("incisive_laterale_droit_haut",named,lastt,p));
        incisive_centrale_haute_droit_haut = findViewById(R.id.incisive_centrale_haute_droit_haut);
        incisive_centrale_haute_droit_haut.setOnClickListener(v -> saveButtomId("incisive_centrale_haute_droit_haut",named,lastt,p));
        incisive_centrale_haute_gauche_haut = findViewById(R.id.incisive_centrale_haute_gauche_haut);
        incisive_centrale_haute_gauche_haut.setOnClickListener(v -> saveButtomId("incisive_centrale_haute_gauche_haut",named,lastt,p));
        Intent intent = getIntent();
        if (intent.getExtras() != null) {

            patient = (Patient) intent.getSerializableExtra("patient");
             named = patient.getName();

            lastt = patient.getLastname();


            int phoned = patient.getPhone();
             p = String.valueOf(phoned);



        }
    }
    private void  saveButtomId(String id,String name ,String last ,String phone )
    {
        sharedPreferences = getApplicationContext().getSharedPreferences("Dentscheck", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("id",id);
        editor.putString("name",name);
        editor.putString("last",last);
        editor.putString("phone",phone);



        editor.apply();
        Intent i = new Intent(this, patientDetails.class);
        startActivity(i);


    }
}