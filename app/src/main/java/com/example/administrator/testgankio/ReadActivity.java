package com.example.administrator.testgankio;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.administrator.testgankio.adpter.MyPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/6/15.
 */
public class ReadActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager vp;
    private ArrayList<Fragment> fragments;
    private  final  String[]mTitle={"Android","iOS","前端","拓展资源"};
    private int numToSetCurrentItem=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CoderfunCache.isBackFromWebOrImage=true;
        numToSetCurrentItem=getIntent().getIntExtra("numToSetCurrentItem",0);
        initToolbar();
        initFragments();
        initViewPager();
        initTabLayout();
        vp.setCurrentItem(numToSetCurrentItem);
    }

    private void initTabLayout() {
        tabLayout= (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(vp);

    }

    private void initViewPager() {
        vp= (ViewPager) findViewById(R.id.vp);
        vp.setOffscreenPageLimit(4);
        vp.setAdapter(new MyPagerAdapter(getSupportFragmentManager(),fragments,mTitle));

    }

    private void initFragments() {
        fragments=new ArrayList<>();
        for (String title:mTitle){
            fragments.add(ReadFragment.getInstance(title));
        }

    }

    private void initToolbar() {
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_white_24dp);
        getSupportActionBar().setTitle("分类阅读");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);

    }
}
