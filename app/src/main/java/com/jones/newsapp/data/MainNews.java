package com.jones.newsapp.data;

import com.jones.newsapp.model.DataModel;

import java.util.ArrayList;

public class MainNews {

    private String status;
    private int totalResults;
    private ArrayList<DataModel> articles;

    public MainNews(String status, int totalResults, ArrayList<DataModel> articles) {
        this.status = status;
        this.totalResults = totalResults;
        this.articles = articles;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public ArrayList<DataModel> getArticles() {
        return articles;
    }

    public void setArticles(ArrayList<DataModel> articles) {
        this.articles = articles;
    }
}
