package com.jones.newsapp.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.jones.newsapp.controller.AppController;
import com.jones.newsapp.fragments.EntertainmentFragment;
import com.jones.newsapp.fragments.HealthFragment;
import com.jones.newsapp.fragments.HomeFragment;
import com.jones.newsapp.fragments.ScienceFragment;
import com.jones.newsapp.fragments.SportsFragment;
import com.jones.newsapp.fragments.TechFragment;

public class PagerAdapter extends FragmentPagerAdapter {

    int tabCount;

    public PagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        this.tabCount = behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return AppController.getInstance().getHomeFragment();
            case 1 :
                return AppController.getInstance().getSportsFragment();
            case 2:
                return AppController.getInstance().getHealthFragment();
            case 3:
                return AppController.getInstance().getScienceFragment();
            case 4:
                return AppController.getInstance().getEntertainmentFragment();
            case 5:
                return AppController.getInstance().getTechFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
