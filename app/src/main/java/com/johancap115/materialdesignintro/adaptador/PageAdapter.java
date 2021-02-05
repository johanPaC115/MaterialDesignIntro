package com.johancap115.materialdesignintro.adaptador;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class PageAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> myFragments;

    public PageAdapter(FragmentManager fm, ArrayList<Fragment> fragments) {
        super(fm);
        this.myFragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return myFragments.get(position);
    }

    @Override
    public int getCount() {
        return myFragments.size();
    }
}
