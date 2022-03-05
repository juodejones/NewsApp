package com.jones.newsapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jones.newsapp.R;
import com.jones.newsapp.adapter.RecyclerViewAdapter;
import com.jones.newsapp.data.api.MainNews;
import com.jones.newsapp.data.api.NewsApiRepository;
import com.jones.newsapp.data.api.Repository;
import com.jones.newsapp.model.DataModel;
import com.jones.newsapp.model.NewsApiViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private String api = "e0a3b53546b740658fed055c7220774d";
    private String country = "in";

    List<DataModel> dataModels;
    RecyclerViewAdapter adapter;
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.homefragment, null);

        recyclerView = view.findViewById(R.id.recyclerview_of_home);
        dataModels = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new RecyclerViewAdapter(getContext(), dataModels);
        recyclerView.setAdapter(adapter);

        Repository.getRetrofit().getNews(country, 100, api).enqueue(new Callback<MainNews>() {
            @Override
            public void onResponse(Call<MainNews> call, Response<MainNews> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    dataModels.addAll(response.body().getArticles());
                    adapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onFailure(Call<MainNews> call, Throwable t) {

            }
        });


        return view;
    }


}
