package com.example.joy.cancerhub.adapters;

import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.joy.cancerhub.R;
import com.example.joy.cancerhub.models.HelpModel;
import com.example.joy.cancerhub.models.Protect;

import java.util.List;

public class ProtectAdapter extends RecyclerView.Adapter<ProtectAdapter.MyViewHolder> {

    private List<Protect> helpList;

    public ProtectAdapter(List<Protect> helpList) {
        this.helpList = helpList;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_protect, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Protect helpModel = helpList.get(position);
        holder.helpTitle.setText(helpModel.getTitle());
        holder.helpInfo.setImageResource(helpModel.getimg());
    }

    @Override
    public int getItemCount() {
        return helpList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView helpTitle; ImageView helpInfo;

        public MyViewHolder(View itemView) {
            super(itemView);
            helpTitle = itemView.findViewById(R.id.textVTitle);
            helpInfo = itemView.findViewById(R.id.image);
        }
    }
}
