package com.example.joy.cancerhub.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.joy.cancerhub.R;
import com.example.joy.cancerhub.models.News;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder> {

    private List<News>newsList;
    public NewsAdapter(List<News> newsList) {
        this.newsList = newsList;

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView news_title, short_descrption, news_med, news_link;
        public MyViewHolder(View itemView) {
            super(itemView);

            news_title = itemView.findViewById(R.id.textViewTitle);
            short_descrption =itemView.findViewById(R.id.textViewShortDesc);
            news_med = itemView.findViewById(R.id.textViewMedNews);
            news_link = itemView.findViewById(R.id.textViewLink);
        }
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View itemView = LayoutInflater.from(parent.getContext())
               .inflate(R.layout.layout_news,parent,false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        News news = newsList.get(position);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/YYYY HH:mm", Locale.getDefault());
        Date recordDate = news.getShortdesc() == null ? new Date() : news.getShortdesc().toDate();
        String date = simpleDateFormat.format(recordDate);
        holder.short_descrption.setText(date);

        holder.news_title.setText(news.getTitle());
        holder.news_med.setText(news.getMedNews());
        holder.news_link.setText(news.getNewsLink());


    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }


}
