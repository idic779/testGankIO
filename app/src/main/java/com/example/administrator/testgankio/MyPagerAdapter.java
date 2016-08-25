package com.example.administrator.testgankio;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/5/25.
 */
public class MyPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> mFragment=new ArrayList<>();
    private String[] mTitles;

    public MyPagerAdapter(FragmentManager fm,ArrayList<Fragment>fragments,String[]mTitles) {
        super(fm);
        this.mFragment=fragments;
        this.mTitles=mTitles;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragment.get(position);
    }

    @Override
    public int getCount() {
        return mFragment.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
