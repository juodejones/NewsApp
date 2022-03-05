package com.jones.newsapp.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.jones.newsapp.model.News;

import java.util.List;

@Dao
public interface NewsDao {

    @Insert
    void insert(News news);

    @Query("SELECT * FROM fav_table")
    LiveData<List<News>> getAll();
}
