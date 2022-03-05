package com.jones.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.jones.newsapp.adapter.FavAdapter;
import com.jones.newsapp.adapter.RecyclerViewAdapter;
import com.jones.newsapp.model.News;
import com.jones.newsapp.model.NewsViewModel;

import java.util.ArrayList;
import java.util.List;

public class Favourites extends AppCompatActivity {

    private NewsViewModel newsViewModel;
    private RecyclerView recyclerView;
    private FavAdapter adapter;
    private List<News> newsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);
        recyclerView = findViewById(R.id.fav_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        newsList = new ArrayList<>();
        adapter = new FavAdapter(getApplicationContext(), newsList);
        recyclerView.setAdapter(adapter);

        newsViewModel = new ViewModelProvider.AndroidViewModelFactory(this.getApplication()).create(NewsViewModel.class);
        newsViewModel.getNewsList().observe( this, news -> {
            newsList = news;
            adapter.updateItem(newsList);
        });
    }

}