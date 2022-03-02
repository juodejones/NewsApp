package com.jones.newsapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.jones.newsapp.model.DataModel;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    Context context;
    List<DataModel> dataList;

    public RecyclerViewAdapter(Context context, List<DataModel> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {

        DataModel data = dataList.get(position);

        holder.cardView.setOnClickListener( view -> {
            Intent intent = new Intent(context, WebView.class);
            intent.putExtra("url", data.getUrl());
            context.startActivity(intent);
        });

        holder.heading.setText(data.getTitle());
        holder.content.setText(data.getDescription());
        holder.author.setText("By : " + data.getAuthor());
        holder.time.setText("Published at : " + data.getPublishedAt());
        Glide.with(context).load(data.getUrlToImage()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView heading, content, author, category, time;
        CardView cardView;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            heading = itemView.findViewById(R.id.heading);
            content = itemView.findViewById(R.id.content);
            imageView = itemView.findViewById(R.id.imageview);
            author = itemView.findViewById(R.id.author);
            time = itemView.findViewById(R.id.published);
            cardView = itemView.findViewById(R.id.cardview);
        }
    }
}
