package com.Dental.Check.Entities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Dental.Check.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class PatientAdapter extends RecyclerView.Adapter<PatientAdapter.PatientAdapterVh> {
    private List<Patient> patients;
    private Context context;
    private ClickedItem clickedItem;

    public PatientAdapter(ClickedItem clickedItem) {
        this.clickedItem=clickedItem;
    }
    public void setData(List<Patient> patients) {
        this.patients = patients;
        notifyDataSetChanged();
    }

    @NonNull
    @NotNull
    @Override
    public PatientAdapterVh onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new PatientAdapter.PatientAdapterVh(LayoutInflater.from(context).inflate(R.layout.row_patients,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull PatientAdapterVh holder, int position) {
        Patient patientResponce= patients.get(position);

        String Patientname = patientResponce.getName();
        holder.Patientname.setText(Patientname);
        holder.imageMore.setOnClickListener(v ->
                clickedItem.clickedPatient(patientResponce));

    }

    public  interface ClickedItem{
        public void clickedPatient(Patient patient);
    }

    @Override
    public int getItemCount() {
        return patients.size();
    }

    public static class PatientAdapterVh extends RecyclerView.ViewHolder {
    TextView Patientname;

    ImageView imageMore;
    public PatientAdapterVh(@NonNull View itemView) {
        super(itemView);
        Patientname = itemView.findViewById(R.id.patientname);
        imageMore = itemView.findViewById(R.id.imageMore);

    }
}
}
