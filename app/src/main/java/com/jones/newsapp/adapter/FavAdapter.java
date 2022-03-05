package com.jones.newsapp.adapter;

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
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.jones.newsapp.R;
import com.jones.newsapp.WebView;
import com.jones.newsapp.model.DataModel;
import com.jones.newsapp.model.News;

import java.util.List;

public class FavAdapter extends RecyclerView.Adapter<FavAdapter.ViewHolder> {

    Context context;
    List<News> dataList;

    public FavAdapter(Context context, List<News> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public FavAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavAdapter.ViewHolder holder, int position) {

        News data = dataList.get(position);

        DataModel dataModel = new DataModel(data.getAuthor(), data.getTitle(), data.getDescription(),
                data.getUrl(), data.getImageUrl(), data.getPublishedAt());

        holder.cardView.setOnClickListener( view -> {
            Intent intent = new Intent(context, WebView.class);
            Gson gson = new Gson();
            intent.putExtra("news", gson.toJson(dataModel));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("favButtonVisible", View.GONE);
            context.startActivity(intent);
        });

        holder.heading.setText(data.getTitle());
        holder.content.setText(data.getDescription());
        holder.author.setText("By : " + data.getAuthor());
        holder.time.setText("Published at : " + data.getPublishedAt());
        Glide.with(context).load(data.getImageUrl()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void updateItem(List<News> newsList){
        dataList = newsList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView heading, content, author, time;
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
