package com.Dental.Check.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.Dental.Check.Entities.Patient;
import com.Dental.Check.R;
import com.Dental.Check.Retrofit.INodeJS;
import com.Dental.Check.activities.Dents;

import java.util.Timer;
import java.util.TimerTask;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class patientDetails extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    TextView name,last,phone;
    String url="http://10.0.2.2:3000/";
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    INodeJS myAPI;
   RadioButton enfant,Adolescents,Adultes,Aines;
   RadioGroup radioGroup;
    private Spinner operationdent;
    private SharedPreferences sharedPreferences;

    Button suivant,cancel;
    private static final String[] operations = {"Selectionnez opération...","Adjonctions et reparation de prothese dentaires",
            "Chirugie buccale","Chirugie maxilo-faciale"," Radiographic","Gouttieres,contentions",
            "Implantologie","Parodontologie","Pose protheses supraimplantaires","Prophylaxie bucco-dentaire","prothese",
            "Détartage", "Traitement d'une carie une face ","Endodontie ", "Traitement d'une carie deux faces",
            "Traitement d'une carie 3 faces et plus " , "Dévitalisation d'une incisive ou d'une canine",
            "Dévitalisation d'une prémolaire ", "Dévitalisation d'une molaire " , "Extraction d'une dent de lait " ,
            "Extraction d'une dent ", "Couronne","Inlay-core","Inlay-core à clavette","Appareil dentaire",
            "Appareil dentaire complet", "Bridge de trois éléments"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_details);
        enfant = findViewById(R.id.enfant);
        Adolescents = findViewById(R.id.Adolescents);
        Adultes= findViewById(R.id.Adultes);
        Aines= findViewById(R.id.Aines);
        name=findViewById(R.id.name);
        last=findViewById(R.id.lastt);
        phone=findViewById(R.id.pphone);
        radioGroup=findViewById(R.id.radioGg);
        suivant=findViewById(R.id.btn_suivant);
        cancel = findViewById(R.id.btn_cancel);

       operationdent = (Spinner)findViewById(R.id.spinner2);
      ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(patientDetails.this, android.R.layout.simple_spinner_item,operations);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        operationdent.setAdapter(adapter1);
        operationdent.setOnItemSelectedListener(this);

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


          Intent i  = new Intent(patientDetails.this, consultation.class);
         if (operationdent.getSelectedItemPosition()==4)
         {

          startActivity(i);
         }
         else if(operationdent.getSelectedItemPosition()==0||!enfant.isChecked()||!Adolescents.isChecked()||!Adultes.isChecked()||!Aines.isChecked()) {
             Toast.makeText(patientDetails.this, "veillez remplir tous les informations!", Toast.LENGTH_SHORT).show();


         }
         else {
             String nom =sharedPreferences.getString("name", "Not found");
             String prenom = sharedPreferences.getString("last", "Not found");
             String numero = sharedPreferences.getString("phone", "Not found");
             if (enfant.isChecked()) {
                // sauvgarde();
             }
             else if (Aines.isChecked())
             {

             }
             else if (Adolescents.isChecked())
             {

             }
             else{

             }
         }

      }
  });
        sharedPreferences = getSharedPreferences("Dentscheck", Context.MODE_PRIVATE);
        Intent intent =getIntent();

           name.setText(sharedPreferences.getString("name", "Not found"));

            last.setText(sharedPreferences.getString("last", "Not found"));


           phone.setText(sharedPreferences.getString("phone", "Not found"));






    }





    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {



               // System.out.print(parent.getSelectedItem());
       // Toast.makeText(patientDetails.this, parent.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();





    }
    public void sauvgarde(String Nompatient, String prenompatient, String numero, String age , String operation , String image ,
                          String state ){
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit retrofit = builder.build();
        myAPI = retrofit.create(INodeJS.class);
        compositeDisposable.add(myAPI.addoperations(Nompatient,prenompatient,numero,age,operation,image,state)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) {


                  }
                }));


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}