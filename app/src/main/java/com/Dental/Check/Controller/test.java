package com.Dental.Check.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.Dental.Check.R;

public class test extends AppCompatActivity  {
calander calander;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        calander= (com.Dental.Check.Controller.calander) findViewById(R.id.calander);

    }

  
}