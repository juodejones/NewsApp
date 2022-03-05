package com.jones.newsapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.jones.newsapp.model.DataModel;
import com.jones.newsapp.model.News;
import com.jones.newsapp.model.NewsViewModel;

import java.util.ArrayList;
import java.util.List;

public class WebView extends AppCompatActivity {

    public static boolean present;
    android.webkit.WebView webView;
    NewsViewModel newsViewModel;
    FloatingActionButton fab;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        webView = findViewById(R.id.web_view);
        fab = findViewById(R.id.add_fav_button);

        Intent intent = getIntent();
        Gson gson = new Gson();
        DataModel dataModel = gson.fromJson(intent.getStringExtra("news"), DataModel.class);
        fab.setVisibility(intent.getIntExtra("favButtonVisible", View.VISIBLE));
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(dataModel.getUrl());

        newsViewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication())
                .create(NewsViewModel.class);

        fab.setOnClickListener(view -> {
            News news = new News(dataModel.getTitle().trim(), dataModel.getDescription().trim(),dataModel.getAuthor(),
                    dataModel.getUrl(), dataModel.getUrlToImage(), dataModel.getPublishedAt());

            newsViewModel.getNewsList().observe( this, newsList -> {
                newsList.forEach(temp -> {
                    if (temp.getTitle().equals(news.getTitle())) {
                        present = true;
                    }
                });
            });
            if (!present) {
                newsViewModel.insert(news);
            } else {
                Toast.makeText(this, "Already in Favourites", Toast.LENGTH_SHORT).show();
             }
        });

    }
}