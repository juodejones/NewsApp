package com.jones.newsapp.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.jones.newsapp.data.api.NewsApiRepository;

import java.util.List;

public class NewsApiViewModel extends AndroidViewModel {

    private MutableLiveData<List<DataModel>> newsList;

    public NewsApiViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<DataModel>> getHomeNews(){
        NewsApiRepository.getInstance().homeNews().observe(getApplication(), new Observer<List<DataModel>>() {
            @Override
            public void onChanged(List<DataModel> models) {
                newsList.postValue(models);
            }
        });
        return newsList;
    }
}
