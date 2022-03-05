package com.jones.newsapp.data.api;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.jones.newsapp.model.DataModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsApiRepository {

    private static NewsApiRepository INSTANCE;
    private String api = "e0a3b53546b740658fed055c7220774d";
    private String country = "in";

    MutableLiveData<List<DataModel>> newsLiveData;

    public static synchronized NewsApiRepository getInstance() {
        if (INSTANCE == null) INSTANCE = new NewsApiRepository();
        return INSTANCE;
    }

    public LiveData<List<DataModel>>  homeNews() {
        newsLiveData = new MutableLiveData<List<DataModel>>();
        Repository.getRetrofit().getNews(country, 100, api).enqueue(new Callback<MainNews>() {
            @Override
            public void onResponse(Call<MainNews> call, Response<MainNews> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    newsLiveData.setValue(response.body().getArticles());
                }
            }
            @Override
            public void onFailure(Call<MainNews> call, Throwable t) {

            }
        });
        return newsLiveData;
    }
}
