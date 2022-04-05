package com.Dental.Check.Entities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.Dental.Check.R;

public class consultation extends AppCompatActivity {
TextView name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultation);
        name =findViewById(R.id.name);


    }

}