package com.technology.givol.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.technology.givol.Tab1;

public class ViewPagerAdapter extends
        FragmentStatePagerAdapter {
    int mNumOfTabs;
    Fragment fragment = null;


    public ViewPagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        Fragment fragment = null;
        fragment = Tab1.newInstance();
        return fragment;


    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return super.getPageTitle(position);
    }
}