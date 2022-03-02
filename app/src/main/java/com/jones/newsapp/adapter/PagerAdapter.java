package com.jones.newsapp.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

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
                return new HomeFragment();
            case 1 :
                return new SportsFragment();
            case 2:
                return new HealthFragment();
            case 3:
                return new ScienceFragment();
            case 4:
                return new EntertainmentFragment();
            case 5:
                return new TechFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
