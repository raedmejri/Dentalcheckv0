package com.Dental.Check.Controller;
import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.TimePickerDialog;
import android.content.Context;


import android.content.Intent;
import android.icu.util.Calendar;
import android.icu.util.TimeZone;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.Dental.Check.Entities.Event;
import com.Dental.Check.R;
import com.Dental.Check.Retrofit.INodeJS;
import com.Dental.Check.activities.RegisterActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Date;
import java.util.List;
import java.util.Locale;


import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


@RequiresApi(api = Build.VERSION_CODES.N)
public class calander extends LinearLayout  {
    AlertDialog alertDialog;
    INodeJS myAPI;
   MyGridAdaptater myGridAdaptater;
    ImageButton previousbtn,nextbtn;
    Button paiment_btn,message_btn,peopel_btn,home_btn;
    List<Event> listev= new ArrayList<>();
    TextView currentday;
    GridView gridView;
    BottomNavigationView buttnavg;
    private final static int  max_calander_days=42;
    Calendar calendar= Calendar.getInstance(Locale.FRANCE);
    Context context;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    List<Date> dates= new ArrayList<>();
    SimpleDateFormat dateFormat=new SimpleDateFormat("MMMM yyyy",Locale.FRANCE);
    SimpleDateFormat jour=new SimpleDateFormat("dd",Locale.FRANCE);
    SimpleDateFormat moisFormat = new SimpleDateFormat("MMMM", Locale.FRANCE);
    SimpleDateFormat anneeFormat = new SimpleDateFormat("yyyy" ,Locale.FRENCH);
    SimpleDateFormat eventformat = new SimpleDateFormat("dd-MM-yyyy",Locale.ENGLISH);



    public calander(Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
        this.context = context;
        IntializeLayout();
        elements(moisFormat.format(calendar.getTime()),anneeFormat.format(calendar.getTime()));


        previousbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.add(Calendar.MONTH, -1);
              //  elements(moisFormat.format(calendar.getTime()),anneeFormat.format(calendar.getTime()));

                SetUpClander();
            }
        });
        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.add(Calendar.MONTH, 1);
                //elements(moisFormat.format(calendar.getTime()),anneeFormat.format(calendar.getTime()));

                SetUpClander();
            }
        });
        buttnavg.setOnNavigationItemSelectedListener(navlistner);



        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setCancelable(true);
                View addView = LayoutInflater.from(parent.getContext()).inflate(R.layout.add_newconsultation_layout, null);
                EditText Nompatient = addView.findViewById(R.id.Nom_patient);
                EditText prenompatient = addView.findViewById(R.id.prenom_patient);
                EditText numero = addView.findViewById(R.id.num_patient);
                TextView EventIime = addView.findViewById(R.id.eventtime);
                ImageButton SetTime = addView.findViewById(R.id.seteventtime);
                Button AddEvent = addView.findViewById(R.id.addevent);
                SetTime.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Calendar calendar = Calendar.getInstance();
                        int hours = calendar.get(Calendar.HOUR_OF_DAY);
                        int minuts = calendar.get(Calendar.MINUTE);
                        TimePickerDialog timePickerDialog = new TimePickerDialog(addView.getContext(),
                                R.style.Base_Theme_AppCompat_Dialog, new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                Calendar c = Calendar.getInstance();
                                c.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                c.set(Calendar.MINUTE, minute);
                                c.setTimeZone(TimeZone.getDefault());
                                SimpleDateFormat hformate = new SimpleDateFormat("HH:mm ", Locale.FRENCH);
                                String event_Time = hformate.format(c.getTime());
                                EventIime.setText(event_Time);
                            }
                        }, hours, minuts, false);
                        timePickerDialog.show();

                    }
                });
                final String date =eventformat.format (dates.get (position));
                final String month =moisFormat.format (dates.get (position));
               final  String year= anneeFormat.format (dates.get (position));
                AddEvent.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {



        addconsultation(Nompatient.getText().toString(),
                prenompatient.getText().toString(),
                numero.getText().toString(),
                EventIime.getText().toString(),
                date);
                        elements(moisFormat.format(calendar.getTime()),anneeFormat.format(calendar.getTime()));


                        SetUpClander();
        alertDialog.dismiss();

         }
                });
                builder.setView (addView);
                alertDialog = builder.create ();
                alertDialog.show ();

            }


        });
        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
               String dateev =eventformat.format(dates.get(position));
                AlertDialog.Builder builder= new AlertDialog.Builder(context);
                builder.setCancelable(true);
                View showviewholder = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_events_layout,null);
                RecyclerView recyclerView=showviewholder.findViewById(R.id.EventsRV);
                RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(showviewholder.getContext());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setHasFixedSize(true);
                EventrecycleAdapter eventrecycleAdapter= new EventrecycleAdapter(showviewholder.getContext()
                        ,collecteventbydate(dateev));
                recyclerView.setAdapter(eventrecycleAdapter);
                eventrecycleAdapter.notifyDataSetChanged();
                builder.setView(showviewholder);
                alertDialog= builder.create();
                alertDialog.show();
                Button cancel=showviewholder.findViewById(R.id.cancel);
                cancel.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });

             return true;
            }
        });

    }
private BottomNavigationView.OnNavigationItemSelectedListener navlistner= new BottomNavigationView.OnNavigationItemSelectedListener() {
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment selectedfragment= null;
        switch (menuItem.getItemId())
        {
            case R.id.navigation_home:

               Intent intent= new Intent(getContext(),test.class);

                    getContext().startActivity(intent);


                break;
            case R.id.chat:
                selectedfragment= new ChatFragment();
                break;
            case R.id.allcansu:
                Intent intent1= new Intent(context, RegisterActivity.class);
                context.startActivity(intent1);
                break;
        }
       return true;
    }
};



    private ArrayList<Event> collecteventbydate (String date)
    {

        ArrayList<Event> arrayList= new ArrayList<>();


        Retrofit retrofit = new  Retrofit.Builder().
                baseUrl("http://10.0.2.2:3000/").
                addConverterFactory(GsonConverterFactory.create())
                .build();
        myAPI = retrofit.create(INodeJS.class);
        Call<List<Event>> call=myAPI.getconsultdate(date);
        call.enqueue(new Callback<List<Event>>() {
            @Override
            public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                List<Event> data = response.body();

                for (int i=0; i<data.size();i++)

                {
                    elements(moisFormat.format(calendar.getTime()),anneeFormat.format(calendar.getTime()));


                    Event events= new Event(response.body().get(i).getIdc(),response.body().get(i).getNom(),response.body().get(i).getLastname(),
                            response.body().get(i).getTime(),response.body().get(i).getDate(),
                           response.body().get(i).getPhone());

                    arrayList.add(events);

                }
               // Toast.makeText(context, "consultation ajouter avec succée!", Toast.LENGTH_SHORT).show();



            }

            @Override
            public void onFailure(Call<List<Event>> call, Throwable t) {
                Toast.makeText(context, "erreur consultation introuvable!", Toast.LENGTH_SHORT).show();

            }
        });


return arrayList;
    }


  public calander(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
      IntializeLayout();
      elements(moisFormat.format(calendar.getTime()),anneeFormat.format(calendar.getTime()));


  }

    public calander(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    private void IntializeLayout() {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view;
            view = inflater.inflate(R.layout.activity_calander, this);
            nextbtn = view.findViewById(R.id.nextbtn);
            previousbtn = view.findViewById(R.id.previousBtn);
            currentday = view.findViewById(R.id.datecourant);
            gridView = view.findViewById(R.id.gridview);
            buttnavg = view.findViewById(R.id.buttnavg);




            rr();
            elements(moisFormat.format(calendar.getTime()),anneeFormat.format(calendar.getTime()));


        }

        public void rr(){
            String currentdate = dateFormat.format(calendar.getTime());
            currentday.setText(currentdate);
            dates.clear();
            Calendar monthCalendar = (Calendar) calendar.clone();
            monthCalendar.set(Calendar.DAY_OF_MONTH, 1);
            int FirstDayofMonth = monthCalendar.get(Calendar.DAY_OF_WEEK) - 1;
            monthCalendar.add(Calendar.DAY_OF_MONTH, -FirstDayofMonth);
            elements(moisFormat.format(calendar.getTime()),anneeFormat.format(calendar.getTime()));
            while (dates.size() < max_calander_days) {
                dates.add(monthCalendar.getTime());
                monthCalendar.add(Calendar.DAY_OF_MONTH, 1);


            }
            myGridAdaptater= new MyGridAdaptater(context,dates,calendar,listev);
            gridView.setAdapter(myGridAdaptater);
            elements(moisFormat.format(calendar.getTime()),anneeFormat.format(calendar.getTime()));

        }
        public void SetUpClander() {
            String currentdate = dateFormat.format(calendar.getTime());
            currentday.setText(currentdate);
            dates.clear();
            Calendar monthCalendar = (Calendar) calendar.clone();
            monthCalendar.set(Calendar.DAY_OF_MONTH, 1);
            int FirstDayofMonth = monthCalendar.get(Calendar.DAY_OF_WEEK) - 1;
            monthCalendar.add(Calendar.DAY_OF_MONTH, -FirstDayofMonth);
            elements(moisFormat.format(calendar.getTime()),anneeFormat.format(calendar.getTime()));
            while (dates.size() < max_calander_days) {
                dates.add(monthCalendar.getTime());
                monthCalendar.add(Calendar.DAY_OF_MONTH, 1);


            }
            myGridAdaptater= new MyGridAdaptater(context,dates,calendar,listev);
            gridView.setAdapter(myGridAdaptater);
            elements(moisFormat.format(calendar.getTime()),anneeFormat.format(calendar.getTime()));


        }

    public void elements( String month, String year)
    {
      Retrofit retrofit = new  Retrofit.Builder().
        baseUrl("http://10.0.2.2:3000/").
        addConverterFactory(GsonConverterFactory.create())
        .build();
        myAPI = retrofit.create(INodeJS.class);
        Call<List<Event>> call=myAPI.getAllconsult();
        call.enqueue(new Callback<List<Event>>() {
            @Override
            public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                List<Event> data = response.body();


                for (int i=0; i<data.size();i++)

                {
                    //System.out.println(response.body().get(i).getIdc());

                  Event events= new Event(response.body().get(i).getIdc(),response.body().get(i).getNom(),
                          response.body().get(i).getLastname(),response.body().get(i).getTime(),response.body().get(i).getDate()
                           ,response.body().get(i).getPhone());

                   listev.add(events);

                }


            }

            @Override
            public void onFailure(Call<List<Event>> call, Throwable t) {

            }
        });

      }
    private void addconsultation (String Nompatient,String prenompatient ,String numero, String time, String date)
    {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3000/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit retrofit = builder.build();
        myAPI = retrofit.create(INodeJS.class);

        compositeDisposable.add(myAPI.addconsultation(Nompatient,prenompatient,numero,time,date)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Toast.makeText(context, "consultation ajouter avec succée!", Toast.LENGTH_SHORT).show();
                        elements(moisFormat.format(calendar.getTime()),anneeFormat.format(calendar.getTime()));
                    }
                }));

    }
}




