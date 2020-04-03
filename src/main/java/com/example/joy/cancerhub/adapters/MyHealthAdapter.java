package com.example.joy.cancerhub.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.joy.cancerhub.R;
import com.example.joy.cancerhub.models.Symptoms;

import java.util.ArrayList;
import java.util.List;

public class MyHealthAdapter extends RecyclerView.Adapter<MyHealthAdapter.MyViewHolder> implements Filterable {
    private List<Symptoms> symptomlist;
    private List<Symptoms> symptomlistFull;
    private Context context;

    public MyHealthAdapter(Context context, List<Symptoms> symptomlist) {
        this.symptomlist = symptomlist;
        this.symptomlistFull = symptomlist;
        this.context = context;

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView textView1;
        private CheckBox checkBox1;

         MyViewHolder(View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.row_item);
            checkBox1 = itemView.findViewById(R.id.check_item);
        }

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_symptoms, parent, false);

        return new MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        final Symptoms selectSympt = symptomlistFull.get(position);
        holder.textView1.setText(selectSympt.getDescription());
        holder.itemView.setBackgroundColor(selectSympt.isSelected() ? Color.LTGRAY : Color.WHITE);
        holder.checkBox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                selectSympt.setSelected(b);
                holder.itemView.setBackgroundColor(b ? Color.LTGRAY : Color.WHITE);
            }
        });
        holder.textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectSympt.setSelected(!selectSympt.isSelected());
                holder.checkBox1.setChecked(selectSympt.isSelected());
                holder.itemView.setBackgroundColor(selectSympt.isSelected() ? Color.LTGRAY : Color.WHITE);
            }
        });
    }

    @Override
    public int getItemCount() {
        return symptomlistFull.size();
    }

    public Filter getFilter() {
        return newFilter;
    }

    private Filter newFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            String filterPattern = charSequence.toString().toLowerCase();
            if (filterPattern.isEmpty()) {
                symptomlistFull = symptomlist;
            } else {
                List<Symptoms> filteredList = new ArrayList<>();
                for (Symptoms symptom : symptomlist)
                    if (symptom.getDescription().toLowerCase().contains(filterPattern))
                        filteredList.add(symptom);
                symptomlistFull = filteredList;

            }
            FilterResults results = new FilterResults();
            results.values = symptomlistFull;
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            symptomlistFull = (List<Symptoms>)filterResults.values;
            notifyDataSetChanged();
        }
    };

}
