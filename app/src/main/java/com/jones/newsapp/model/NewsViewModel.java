package com.jones.newsapp.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.jones.newsapp.data.room.NewsRepository;

import java.util.List;

public class NewsViewModel extends AndroidViewModel {

    private LiveData<List<News>> newsList;
    private NewsRepository repository;

    public NewsViewModel(@NonNull Application application) {
        super(application);
        repository = new NewsRepository(application.getApplicationContext());
        newsList = repository.getNewsList();
    }

    public void insert(News news) { repository.insert(news); }

    public LiveData<List<News>> getNewsList() { return newsList; }
}
