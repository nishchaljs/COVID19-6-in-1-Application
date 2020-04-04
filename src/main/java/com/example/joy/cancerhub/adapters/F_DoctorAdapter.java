package com.example.joy.cancerhub.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.joy.cancerhub.R;
import com.example.joy.cancerhub.activities.MakeAppointment;
import com.example.joy.cancerhub.models.Doctor;

import java.util.List;


public class F_DoctorAdapter extends RecyclerView.Adapter<F_DoctorAdapter.MyViewHolder> {
    private List<Doctor> docsList;
    private Context context;

    public F_DoctorAdapter(List<Doctor> docsList, Context context) {
        this.docsList = docsList;
        this.context=context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView docs_name, docs_description;
        public ImageView imageView;
        Button appoinment;

        public MyViewHolder(View itemView) {
            super(itemView);
            docs_name = itemView.findViewById(R.id.textViewName);
            docs_description = itemView.findViewById(R.id.textViewCharges);
            imageView = itemView.findViewById(R.id.imageViewDoc);
            appoinment = itemView.findViewById(R.id.appointment_btn);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_finddoc, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        final Doctor doctor = docsList.get(position);
        holder.docs_name.setText(doctor.getName());
        holder.docs_description.setText(doctor.getDescription());
        holder.appoinment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Uri uri = Uri.parse(doctor.getEmailaddress()); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                context.startActivity(intent);
            }
        });

          Glide.with(context).load(doctor.getPicture()).into(holder.imageView);

    }


    @Override
    public int getItemCount() {
        return docsList.size();
    }
    public void filterList(List<Doctor> filteredList) {
        this.docsList = filteredList;
        notifyDataSetChanged();
    }
} 
