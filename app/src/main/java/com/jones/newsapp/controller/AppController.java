package com.jones.newsapp.controller;

import android.app.Application;
import android.util.Log;

import com.jones.newsapp.fragments.EntertainmentFragment;
import com.jones.newsapp.fragments.HealthFragment;
import com.jones.newsapp.fragments.HomeFragment;
import com.jones.newsapp.fragments.ScienceFragment;
import com.jones.newsapp.fragments.SportsFragment;
import com.jones.newsapp.fragments.TechFragment;

public class AppController extends Application {

    public static AppController instance;

    private HomeFragment homeFragment;
    private HealthFragment healthFragment;
    private EntertainmentFragment entertainmentFragment;
    private ScienceFragment scienceFragment;
    private SportsFragment sportsFragment;
    private TechFragment techFragment;


    public HomeFragment getHomeFragment() {
        return homeFragment;
    }

    public HealthFragment getHealthFragment() {
        return healthFragment;
    }

    public EntertainmentFragment getEntertainmentFragment() {
        return entertainmentFragment;
    }

    public ScienceFragment getScienceFragment() {
        return scienceFragment;
    }

    public SportsFragment getSportsFragment() {
        return sportsFragment;
    }

    public TechFragment getTechFragment() {
        return techFragment;
    }

    public static synchronized AppController getInstance() {
        return instance;
    }

    private void getFragments() {
        homeFragment = new HomeFragment();
        healthFragment = new HealthFragment();
        entertainmentFragment = new EntertainmentFragment();
        scienceFragment = new ScienceFragment();
        sportsFragment = new SportsFragment();
        techFragment = new TechFragment();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.getFragments();
        instance = this;
    }
}
