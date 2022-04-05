package com.Dental.Check.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.Dental.Check.Entities.User;
import com.Dental.Check.R;

public class UserdetailsActivity extends AppCompatActivity {
TextView name,email,adress,phone;
User user;
ImageView back;
Button btn;
RatingBar ratingBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userdetails);
        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        adress=findViewById(R.id.adress);
        phone=findViewById(R.id.phone);
        btn=findViewById(R.id.but);

        ratingBar=findViewById(R.id.idrating);

        back=findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i  = new Intent(UserdetailsActivity.this, Accueil.class);
                startActivity(i);

            }
        });

        Intent intent =getIntent();
        if (intent.getExtras()!=null){

            user= (User) intent.getSerializableExtra("data");
            String named=user.getName();
            String emaild=user.getEmail();

            int phoned =user.getPhone();
            name.setText(named);
            email.setText(emaild);
            String p= String.valueOf(phoned);
            phone.setText(p);


        }

    }

    public void onBtnClick(View v){
        float ratingvalue = ratingBar.getRating();

            Toast.makeText(UserdetailsActivity.this, "Rating is"+ratingvalue , Toast.LENGTH_SHORT).show();}

}