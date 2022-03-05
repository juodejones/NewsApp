package com.jones.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.widget.SearchView;

import com.jones.newsapp.adapter.RecyclerViewAdapter;
import com.jones.newsapp.data.api.MainNews;
import com.jones.newsapp.data.api.Repository;
import com.jones.newsapp.model.DataModel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {


    SearchView searchView;
    String api = "e0a3b53546b740658fed055c7220774d";
    List<DataModel> dataModels;
    RecyclerViewAdapter adapter;
    String country = "in";
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchView = findViewById(R.id.search_view);
        recyclerView = findViewById(R.id.recyclerview_of_search);
        dataModels = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecyclerViewAdapter(this, dataModels);
        recyclerView.setAdapter(adapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                Repository.getRetrofit().getSearchNews(s, api).enqueue(new Callback<MainNews>() {
                    @Override
                    public void onResponse(Call<MainNews> call, Response<MainNews> response) {
                        if (response.isSuccessful()) {
                            assert response.body() != null;
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                List<DataModel> data = response.body().getArticles().stream()
                                        .filter(dataModel -> dataModel.getTitle().toUpperCase().contains(s.toUpperCase()))
                                        .collect(Collectors.toList());
                                adapter = new RecyclerViewAdapter(SearchActivity.this, data);
                                recyclerView.setAdapter(adapter);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<MainNews> call, Throwable t) {

                    }
                });
                return false;
            }
        });

    }
}