package com.example.sushantpaygude.finalproject.Activities;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.sushantpaygude.finalproject.Adapters.ViewPagerAdapter;
import com.example.sushantpaygude.finalproject.Fragments.RestaurantsFragment;
import com.example.sushantpaygude.finalproject.R;
import com.example.sushantpaygude.finalproject.Utils.Utilities;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout = findViewById(R.id.tabs);
        viewPager = findViewById(R.id.viewPager);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
    }



    private void setupViewPager(ViewPager viewPager)
    {
        viewPagerAdapter.addFragment(new RestaurantsFragment(), Utilities.RESTAURANTS);
        viewPagerAdapter.addFragment(new RestaurantsFragment(), "Two");
        viewPagerAdapter.addFragment(new RestaurantsFragment(), "Three");

        viewPager.setAdapter(viewPagerAdapter);
    }

}
