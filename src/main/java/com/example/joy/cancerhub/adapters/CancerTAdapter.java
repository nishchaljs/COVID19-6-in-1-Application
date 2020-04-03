package com.example.joy.cancerhub.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.joy.cancerhub.R;
import com.example.joy.cancerhub.activities.CancerTypeInfo;
import com.example.joy.cancerhub.models.CancerType;

import java.util.List;

public class CancerTAdapter extends RecyclerView.Adapter<CancerTAdapter.MyViewHolder> {

    private Context mContext;
    private List<CancerType> cancerList;


    public CancerTAdapter(Context context, List<CancerType> cancerList) {
        this.mContext = context;
        this.cancerList = cancerList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView textCancer;

        MyViewHolder(View itemView) {
            super(itemView);
            textCancer = itemView.findViewById(R.id.textVCancerType);

        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_cancer_types, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final CancerType cancer = cancerList.get(position);
        holder.textCancer.setText(cancer.getName());
        holder.textCancer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(mContext, CancerTypeInfo.class);
                Bundle bundle = new Bundle();
                bundle.putString("cancer_type_id", cancer.getId());
                bundle.putString("cancer_name", cancer.getName());
                intent.putExtras(bundle);
                mContext.startActivity(intent);


            }
        });

    }

    @Override
    public int getItemCount() {
        return cancerList.size();
    }

}