package com.Dental.Check.Controller;

import android.content.Context;
import android.graphics.Color;
import android.icu.util.Calendar;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.Dental.Check.Entities.Event;
import com.Dental.Check.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MyGridAdaptater extends ArrayAdapter {

    List<Date> dates;
    Calendar currentDate;
    List<Event> listev;
    LayoutInflater inflater;




    public MyGridAdaptater(Context context, List<Date> dates, Calendar currentDate, List<Event> listev) {
        super(context, R.layout.singel_cell_layout);
        this.dates = dates;
        this.currentDate = currentDate;
        this.listev = listev;
        inflater = LayoutInflater.from(context);
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        Date monthDate=dates.get (position);

        Calendar dateCalendar =Calendar.getInstance ();
        dateCalendar.setTime(monthDate);
        int DayNo = dateCalendar.get (Calendar.DAY_OF_MONTH) ;
        int displayMonth =dateCalendar.get (Calendar. MONTH) +1;
        int displayYear = dateCalendar.get (Calendar. YEAR) ;
        int currentMonth = currentDate.get (Calendar.MONTH) +1;
        int currentYear = currentDate .get (Calendar. YEAR) ;
        View view = convertView;
        if (view ==null) {
            view = inflater.inflate(R.layout.singel_cell_layout, parent,false);
        }
            if ((displayMonth == currentMonth) && (displayYear == currentYear))
            {
                view.setBackgroundColor(getContext().getResources().getColor(R.color.raed));
            }
            else {
                view.setBackgroundColor(Color.parseColor("#322e2f"));

            }
        TextView Day_Number= view.findViewById (R.id.calendar_day);
        TextView Eventnumber = view.findViewById(R.id.events_id);
        Day_Number.setText (String.valueOf(DayNo) );
        Calendar eventCalendar =Calendar.getInstance ();
        ArrayList<Integer> arrayList = new ArrayList<>();

        for (int i= 0;i< listev.size ();i++)
        {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                eventCalendar.setTime(convertstringdate(listev.get(i).getDate()));
            }


            if (  DayNo == eventCalendar.get(Calendar.DAY_OF_MONTH)&& displayMonth== eventCalendar.get(Calendar.MONTH)+1
            &&  displayYear==eventCalendar.get(Calendar.YEAR))

             {

//System.out.println(listev.get(i).getIdc());
                arrayList.add(listev.get(i).getIdc());
                Eventnumber.setTextColor(Color.parseColor("#e2d810"));
                Eventnumber.setText(" Voir consultations ");
            }

        }


        return view;
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public Date convertstringdate(String eventdate )
    {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);

                Date date = null;

        try {
             date = formatter.parse(eventdate);


        }
        catch (Exception exception)
        {
            Context context = null;


        }
return date;
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return dates.get(position);
    }

    @Override
    public int getPosition(@Nullable Object item) {
        return dates.indexOf(item);
    }


    @Override
    public int getCount() {
        return dates.size();
    }
}
