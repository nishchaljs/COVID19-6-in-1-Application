package com.example.joy.cancerhub.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.joy.cancerhub.R;
import com.example.joy.cancerhub.models.Prognosis;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class RecordsAdapter extends RecyclerView.Adapter<RecordsAdapter.ViewHolder> {

    private Context context;
    private List<Prognosis> records;

    public RecordsAdapter(Context context, List<Prognosis> records) {
        this.records = records;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView dateOfRecord, txtRiskRecord, txtCancerRecord, txtRecommendRecord, txtRecommendRecord2;

        public ViewHolder(View itemView) {
            super(itemView);
            dateOfRecord = itemView.findViewById(R.id.dateOfRecord);
            txtRiskRecord = itemView.findViewById(R.id.txtRiskRecord);
            txtCancerRecord = itemView.findViewById(R.id.txtCancerRecord);
            txtRecommendRecord = itemView.findViewById(R.id.txtRecommendRecord);
            txtRecommendRecord2 = itemView.findViewById(R.id.txtRecommendRecord2);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_records, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Prognosis record = records.get(position);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/YYYY HH:mm", Locale.getDefault());
        Date recordDate = record.getTimestamp() == null ? new Date() : record.getTimestamp().toDate();
        String date = simpleDateFormat.format(recordDate);
        holder.dateOfRecord.setText(date);
        holder.txtCancerRecord.setText(record.getCancerDisease());
        holder.txtRiskRecord.setText(record.getRiskLevel());
        holder.txtRecommendRecord.setText(record.getRecommendation());
        holder.txtRecommendRecord2.setText(record.getRecommendation());
    }

    @Override
    public int getItemCount() {
        return records.size();
    }


}
