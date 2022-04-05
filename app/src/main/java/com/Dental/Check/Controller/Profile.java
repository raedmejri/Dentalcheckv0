package com.Dental.Check.Controller;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

import com.Dental.Check.R;


public class Profile extends AppCompatActivity {
    TextView logout;
    TextView location;
    TextView ismii;
    TextView mail,phon,adres;
    ImageView imageView , imageview_profile;
    private  SharedPreferences sharedPreferences;
    private  Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ismii = findViewById(R.id.ismi);
        imageView = findViewById(R.id.p1);
        phon=findViewById(R.id.phoone);
        adres=findViewById(R.id.location);

        mail=findViewById(R.id.email);
        sharedPreferences = getSharedPreferences("ekhdem", Context.MODE_PRIVATE);



        ismii.setText(sharedPreferences.getString("name",null));

        mail.setText(sharedPreferences.getString("mail",null));
       phon.setText("+216"+sharedPreferences.getInt("phone",-1));
        adres.setText(sharedPreferences.getString("adress",null));

        //     ismii.setText("bb" + sharedPreferences.getInt("name",-1));




        location = findViewById(R.id.location);

        logout = findViewById(R.id.logout);
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent i = new Intent(Profile.this, MainActivity4.class);
                //startActivity(i);

            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Profile.this, LoginActivity.class);
                startActivity(i);

            }
        });




    }

}