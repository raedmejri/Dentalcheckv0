package com.Dental.Check.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.Dental.Check.Controller.Accueil;
import com.Dental.Check.Controller.test;
import com.Dental.Check.Entities.User;
import com.Dental.Check.Retrofit.INodeJS;
import com.Dental.Check.Retrofit.RetrofitClient;
import com.Dental.Check.R;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity  {
    INodeJS myAPI;
    String url="http://10.0.2.2:3000";
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    TextView id;

    EditText email, password;

    //private ImageButton btRegister;

    //private TextView tvLogin;
    Button Go;
private SharedPreferences sharedPreferences;
    @Override
    protected void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }



    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        TextView btn=findViewById(R.id.textViewSignUp);
        TextView forget=findViewById(R.id.forgotPassword);
        Go = findViewById(R.id.btnlogin);
        email = findViewById(R.id.emailEdit);
        password = findViewById(R.id.passwordEdit);
     forget.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {

         }
     });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });

        sharedPreferences = getSharedPreferences("testt", Context.MODE_PRIVATE);
        email.setText(sharedPreferences.getString("test", ""));
        password.setText(sharedPreferences.getString("test1", ""));

        //Init API
        Retrofit retrofit = RetrofitClient.getInstance();
        myAPI = retrofit.create(INodeJS.class);


        Go.setOnClickListener(v -> {
            String e = email.getText().toString();
            String p = password.getText().toString();
            if (e.equals("") || p.equals("")) {
                Toast.makeText(LoginActivity.this, "Check Your Entries!", Toast.LENGTH_SHORT).show();
            } else {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("test", e);
                editor.putString("test1", p);
                editor.apply();
                loginUser(e, p);
        }
    });
    }






    private void loginUser( String email, String password)
    {
        //Show error from API
        compositeDisposable.add(myAPI.loginUser(email, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s ->
                {
                    if (s.contains("encrypted_password")) {
                        Toast.makeText(LoginActivity.this, "Login avec succsée!", Toast.LENGTH_SHORT).show();
                        sharedPreferences = getApplicationContext().getSharedPreferences("ekhdem", Context.MODE_PRIVATE);
                        String email2 = email;
                        Retrofit.Builder builder = new Retrofit.Builder()
                                .baseUrl(url)
                                .addConverterFactory(GsonConverterFactory.create());
                        Retrofit retrofit = builder.build();
                        myAPI = retrofit.create(INodeJS.class);
                        Call<User> call = myAPI.getUser(email2);

                        call.enqueue(new Callback<User>() {
                            @Override
                            public void onResponse(Call<User> call, Response<User> response) {
                                User user = response.body();

                                if(user.getRole().equals("Dentiste")||user.getRole().equals("dentiste"))
                                {
                                    Intent i = new Intent(LoginActivity.this, Accueil.class);
                                    startActivity(i);
                                }
                                else  if(user.getRole().equals("Secretaire")||user.getRole().equals("secretaire"))
                                {
                                   Intent i = new Intent(LoginActivity.this, test.class);
                                   startActivity(i);
                                }
                                else {
                                    Toast.makeText(LoginActivity.this, "invalide user ", Toast.LENGTH_SHORT).show();

                                }

                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putInt("id", user.getId());
                                editor.putString("name", user.getName());
                                editor.putString("mail", user.getEmail());
                                editor.putInt("phone", user.getPhone());
                                //editor.putString("ima", user.getImaa());
                                editor.putString("ima", user.getRole());


                                editor.apply();

                            }

                            @Override
                            public void onFailure(Call<User> call, Throwable t) {

                            }


                        });

                    }
                    else {
                        Toast.makeText(LoginActivity.this, "vérifier votre mot de passe!" , Toast.LENGTH_SHORT).show();

                    }

                }));



    }




}