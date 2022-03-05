package com.jones.newsapp.data;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.LiveData;

import com.jones.newsapp.model.News;

import java.util.List;

public class NewsRepository {

    private LiveData<List<News>> newsList;
    private NewsDao newsDao;

    public NewsRepository(Context context) {
        NewsRoomDatabase db = NewsRoomDatabase.getDatabase(context);
        this.newsDao = db.newsDao();
        this.newsList = newsDao.getAll();
    }

    public void insert(News news) {
        NewsRoomDatabase.databaseExecutor.execute( () -> {
            newsDao.insert(news);
        });
    }

    public LiveData<List<News>> getNewsList() {
        return newsList;
    }
}
