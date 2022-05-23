package com.Dental.Check.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.Dental.Check.Entities.Patient;
import com.Dental.Check.Retrofit.ApiClient;
import com.Dental.Check.Entities.PatientAdapter;
import com.Dental.Check.R;
import com.Dental.Check.activities.Dents;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Accueil extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, PatientAdapter.ClickedItem{
    static final float END_SCALE= 0.7f;
    LinearLayout contentView;
RecyclerView recyclerView;
    PatientAdapter patientAdapter;
ImageView icon_drawer;




    DrawerLayout drawerLayout;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_acceuil);


      recyclerView = findViewById(R.id.recyclerView);
      recyclerView.setLayoutManager(new LinearLayoutManager(this));
      recyclerView.addItemDecoration( new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
      patientAdapter = new PatientAdapter(this::clickedPatient);
        getAllpatient();
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        icon_drawer = findViewById(R.id.menu_icon);
        contentView = findViewById(R.id.content);

        navigationDrawer();
        Call<List<Patient>> patientList = ApiClient.getpatient().getAllpatient();
        patientList.enqueue(new Callback<List<Patient>>() {
            @Override
            public void onResponse(Call<List<Patient>> call, Response<List<Patient>> response) {
                if (response.isSuccessful()){
                    List<Patient> PatientResponse = response.body();

                    patientAdapter.setData(PatientResponse);
                    recyclerView.setAdapter(patientAdapter);
                }

            }

            @Override
            public void onFailure(Call<List<Patient>> call, Throwable t) {
                Log.e("failure",t.getLocalizedMessage());
            }
        });



    }


    private void navigationDrawer() {
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.home);
        icon_drawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(drawerLayout.isDrawerVisible(GravityCompat.START)){
                    drawerLayout.closeDrawer(GravityCompat.START);
                }else
                    drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        animateNagivationDrawer();
    }

    private void animateNagivationDrawer() {
        drawerLayout.setScrimColor(getResources().getColor(R.color.colorPrimary));
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

                // Scale the View based on current slide offset
                final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1 - diffScaledOffset;
                contentView.setScaleX(offsetScale);
                contentView.setScaleY(offsetScale);

                // Translate the View, accounting for the scaled width
                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = contentView.getWidth() * diffScaledOffset / 2;
                final float xTranslation = xOffset - xOffsetDiff;
                contentView.setTranslationX(xTranslation);
            }
        });
    }

    public void onBackPressed(){
        if(drawerLayout.isDrawerVisible(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else
            super.onBackPressed();
    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.profile:
                Intent i  = new Intent(Accueil.this, Profile.class);
                startActivity(i);
                break;
            case R.id.home:
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
            case R.id.categories:
                //Intent i1  = new Intent(Accueil.this, MesDemandes.class);
                //startActivity(i1);
                break;
            case R.id.categoriess:
                //Intent i2  = new Intent(Accueil.this, MesOffre.class);
                //startActivity(i2);
                break;
        }
        return true;
    }

    public void getAllpatient(){
        Call<List<Patient>> PatientList = ApiClient.getpatient().getAllpatient();
        PatientList.enqueue(new Callback<List<Patient>>() {
            @Override
            public void onResponse(Call<List<Patient>> call, Response<List<Patient>> response) {
                if(response.isSuccessful()){
                    List<Patient> Patients= response.body();
                    patientAdapter.setData(Patients);
                    recyclerView.setAdapter(patientAdapter);
                }
            }


            @Override
            public void onFailure(Call<List<Patient>> call, Throwable t) {
                Log.e("falure",t.getLocalizedMessage());
            }
        });

    }

     @Override
    public void clickedPatient(Patient p1) {


        Intent i = new Intent(this, Dents.class).putExtra("patient",  p1);
        startActivity(i);

    }
}