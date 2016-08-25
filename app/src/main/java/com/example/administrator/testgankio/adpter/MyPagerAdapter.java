package com.example.administrator.testgankio.adpter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/6/18.
 */
public class MyPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment>fragments=new ArrayList<>();
    private String [] mTitles;

    public MyPagerAdapter(FragmentManager fm,ArrayList<Fragment>fragments,String[]titles) {
        super(fm);
        this.fragments=fragments;
        this.mTitles=titles;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];

    }
}
