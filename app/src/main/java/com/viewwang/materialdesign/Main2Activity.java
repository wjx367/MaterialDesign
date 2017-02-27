package com.viewwang.materialdesign;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import butterknife.Bind;
import butterknife.ButterKnife;


public class Main2Activity extends AppCompatActivity {


    @Bind(R.id.id_tablayout)
    TabLayout idTablayout;
    @Bind(R.id.id_viewpager)
    ViewPager idViewpager;
    private int mTabCount = 4;
    private MainFragmentPagerAdapter fragmentPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);
        fragmentPagerAdapter = new MainFragmentPagerAdapter(getSupportFragmentManager(), mTabCount);
        idViewpager.setAdapter(fragmentPagerAdapter);
        idTablayout.setupWithViewPager(idViewpager);
    }
}
