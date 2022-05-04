package com.Dental.Check.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.Dental.Check.Entities.Patient;
import com.Dental.Check.Entities.consultation;
import com.Dental.Check.R;
import com.Dental.Check.activities.Dents;

import java.util.Timer;
import java.util.TimerTask;

public class patientDetails extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    TextView name,last,phone;
    Patient patient;
    ImageView back;
    private Spinner operationdent;
 Timer timer;

    Button suivant,cancel;
    private static final String[] operations = {"Adjonctions et reparation de prothese dentaires",
            "Chirugie buccale","Chirugie maxilo-faciale","Endodontie","Gouttieres,contentions",
            "Implantologie","Parodontologie","Pose protheses supraimplantaires","Prophylaxie bucco-dentaire","prothese",
            "Détartage", "Traitement d'une carie une face ", "Traitement d'une carie deux faces",
            "Traitement d'une carie 3 faces et plus " , "Dévitalisation d'une incisive ou d'une canine",
            "Dévitalisation d'une prémolaire ", "Dévitalisation d'une molaire " , "Extraction d'une dent de lait " ,
            "Extraction d'une dent ", "Couronne","Inlay-core","Inlay-core à clavette","Appareil dentaire",
            "Appareil dentaire complet", "Bridge de trois éléments"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_details);
        name=findViewById(R.id.name);
        last=findViewById(R.id.lastt);
        phone=findViewById(R.id.pphone);
        suivant=findViewById(R.id.btn_suivant);
        cancel = findViewById(R.id.btn_cancel);

       // operationdent = (Spinner)findViewById(R.id.spinner2);
   /*     ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(patientDetails.this, android.R.layout.simple_spinner_item,operations);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        operationdent.setAdapter(adapter1);
        operationdent.setOnItemSelectedListener(this);*/

cancel.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent i  = new Intent(patientDetails.this, Accueil.class);
        startActivity(i);
    }
});
  suivant.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
          Intent i  = new Intent(patientDetails.this, PatientDetaille2.class);
          startActivity(i);
      }
  });

        Intent intent =getIntent();
        if (intent.getExtras()!=null){

            patient= (Patient) intent.getSerializableExtra("patient");
           String named=patient.getName();
           name.setText(named);
            String lastt=patient.getLastname();
            last.setText(lastt);

            int phoned =patient.getPhone();
            String p= String.valueOf(phoned);
           phone.setText(p);


        }
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
Intent i = new Intent(patientDetails.this,PatientDetaille2.class);
startActivity(i);
            }
        },5000000);


    }



   /* public void onBtnClick(View v){

Intent i = new Intent(patientDetails.this, consultation.class).putExtra("patient",patient);
        startActivity(i);

        //Toast.makeText(patientDetails.this, "Rating is"+ratingvalue , Toast.LENGTH_SHORT).show();
    }
*/

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {



               // System.out.print(parent.getSelectedItem());
        Toast.makeText(patientDetails.this, parent.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();





    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}