package com.Dental.Check.Controller;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.icu.util.TimeZone;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.Dental.Check.Entities.Event;
import com.Dental.Check.Entities.Patient;
import com.Dental.Check.R;
import com.Dental.Check.Retrofit.INodeJS;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EventrecycleAdapter extends RecyclerView.Adapter<EventrecycleAdapter.Myviewholder> {
    Context context;
    INodeJS myAPI;
    ArrayList<Event>arrayList;
    AlertDialog alertDialog;

    public EventrecycleAdapter(Context context, ArrayList<Event> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public Myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.show_event_row_layout,parent,false);
        return new Myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Myviewholder holder, int position) {
        Event event=arrayList.get(position);
        holder.lastnameText.setText(event.getLastname());
        holder.nomText.setText(event.getNom());
        holder.timeText.setText(event.getTime());
        holder.dateText.setText(event.getDate());
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View confirme = LayoutInflater.from(builder.getContext()).inflate(R.layout.updateclientconsultation, null);
        String nom= event.getNom();
        String prenom=event.getLastname();
        String phone=event.getPhone();
        EditText nomm=confirme.findViewById(R.id.Npatient);

        EditText eventtime=confirme.findViewById(R.id.eventtime);
        EditText pre=confirme.findViewById(R.id.prpatient);

        EditText nu=confirme.findViewById(R.id.numm_patient);

        ImageButton SetTime = confirme.findViewById(R.id.seteventtime);
        holder.modifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                builder.setCancelable(true);
                 builder.setView(confirme);
                nomm.setText(nom);
                pre.setText(prenom);
                nu.setText(phone);
                alertDialog = builder.create ();
                alertDialog.show ();
                SetTime.setOnClickListener(new View.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onClick(View v) {

                        Calendar calendar = Calendar.getInstance();
                        int hours = calendar.get(Calendar.HOUR_OF_DAY);
                        int minuts = calendar.get(Calendar.MINUTE);
                        TimePickerDialog timePickerDialog= new TimePickerDialog(confirme.getContext(), R.style.Base_Theme_AppCompat_Dialog,
                                new TimePickerDialog.OnTimeSetListener() {
                                    @Override
                                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                        Calendar c = Calendar.getInstance();
                                        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                        c.set(Calendar.MINUTE, minute);
                                        c.setTimeZone(TimeZone.getDefault());
                                        SimpleDateFormat hformate = new SimpleDateFormat("HH:mm ", Locale.FRENCH);
                                        String event_Time = hformate.format(c.getTime());
                                        eventtime.setText(event_Time);

                                    }
                                },hours, minuts, false);
                        timePickerDialog.show();




                    }
                });
                CalendarView calendarView=(CalendarView) confirme.findViewById(R.id.calanderv);
                TextView textView= (TextView) confirme.findViewById(R.id.eventtimeupdate);
                calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                    @Override
                    public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
        if (month+1<=9)
        {
             month=month+1;
            String date=dayOfMonth+"-"+"0"+month +"-"+year;
            textView.setText(date);
        }
        else{
            month=month+1;
        String date=dayOfMonth+"-"+month +"-"+year;
                        textView.setText(date);

        }




                    }
                });

            }
        });

        Button mod = confirme.findViewById(R.id.modifierconsultation);
        mod.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {


                TextView textView= (TextView) confirme.findViewById(R.id.eventtimeupdate);
                ImageButton SetTime = confirme.findViewById(R.id.seteventtime);
                SetTime.setOnClickListener(new View.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onClick(View v) {

                        Calendar calendar = Calendar.getInstance();
                        int hours = calendar.get(Calendar.HOUR_OF_DAY);
                        int minuts = calendar.get(Calendar.MINUTE);
                        TimePickerDialog timePickerDialog= new TimePickerDialog(confirme.getContext(), R.style.Base_Theme_AppCompat_Dialog,
                                new TimePickerDialog.OnTimeSetListener() {
                                    @Override
                                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                        Calendar c = Calendar.getInstance();
                                        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                        c.set(Calendar.MINUTE, minute);
                                        c.setTimeZone(TimeZone.getDefault());
                                        SimpleDateFormat hformate = new SimpleDateFormat("HH:mm ", Locale.FRENCH);
                                        String event_Time = hformate.format(c.getTime());
                                        eventtime.setText(event_Time);

                                    }
                                },hours, minuts, false);
                        timePickerDialog.show();




                    }
                });
                event.setNom(nomm.getText().toString());
                event.setLastname(pre.getText().toString());
                event.setTime(eventtime.getText().toString());
                event.setDate(textView.getText().toString());
                event.setPhone(nu.getText().toString());
                modifierEvent(event.getIdc(),event);
                //modifierPAtient(event.getIdc(),patient);
            }
        });


        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setCancelable(true);
                View confirme = LayoutInflater.from(builder.getContext()).inflate(R.layout.confirmationlayout, null);
                builder.setView(confirme);
                alertDialog = builder.create ();
                alertDialog.show ();
               Button oui=confirme.findViewById(R.id.oui);
                Button non=confirme.findViewById(R.id.non);
                oui.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       // System.out.println(event.getPhone());
                        deletEvent(event.getIdc());
                    }
                });
                non.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });



            }
        });

    }




    public void modifierEvent(int idc, Event event){
       // Log.e("ena hneee" , "dhskjhd"+event.getNom());
        //System.out.println(event.getNom()+event.getDate()+event.getLastname()+event.getTime());
        Retrofit retrofit = new  Retrofit.Builder().
                baseUrl("http://10.0.2.2:3000/").
                addConverterFactory(GsonConverterFactory.create())
                .build();
        myAPI = retrofit.create(INodeJS.class);
        Call<Event> call=myAPI.updateconsult(idc,event);
        call.enqueue(new Callback<Event>() {
            @Override
            public void onResponse(Call<Event> call, Response<Event> response) {
                if (!response.isSuccessful()) {
                 System.out.println(response.code());
                }
                else {
                     Log.e("ena hneee" , "dhskjhd"+response.body().getNom());
                    System.out.println(response.body().getNom());
                }
            }



            @Override
            public void onFailure(Call<Event> call, Throwable t) {

            }
        });
    }
    public void deletEvent( int idc ){
        Retrofit retrofit = new  Retrofit.Builder().
                baseUrl("http://10.0.2.2:3000/").
                addConverterFactory(GsonConverterFactory.create())
                .build();
        myAPI = retrofit.create(INodeJS.class);
        Call<Void> call= myAPI.delete(idc);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful())
                {
                    alertDialog.dismiss();
                    Toast.makeText(context, "suppssion avec succ!!!!", Toast.LENGTH_SHORT).show();


                }

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(context, "echec de suppssion !!!!", Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public class Myviewholder extends RecyclerView.ViewHolder{
        TextView dateText, timeText,nomText,lastnameText;
        Button delete,modifier,oui, non;


        public Myviewholder(@NonNull View itemView) {
            super(itemView);
            dateText=itemView.findViewById(R.id.eventdate);
            timeText=itemView.findViewById(R.id.eventtime);
            nomText=itemView.findViewById(R.id.patientnom);
            lastnameText=itemView.findViewById(R.id.patient_prenom);
            //nn=itemView.findViewById(R.id.Npatient);
            delete = itemView.findViewById(R.id.delete);
            modifier=itemView.findViewById(R.id.modifier);

        }
    }

}
