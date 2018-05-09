package com.example.sushantpaygude.finalproject.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sushantpaygude.finalproject.Adapters.ViewPagerAdapter;
import com.example.sushantpaygude.finalproject.R;
import com.example.sushantpaygude.finalproject.Utils.Utilities;


public class MainFragment extends Fragment {

    //private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        tabLayout = view.findViewById(R.id.tabs);
        viewPager = view.findViewById(R.id.viewPager);
        //toolbar = view.findViewById(R.id.toolbar);
        //view.setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        super.onViewCreated(view, savedInstanceState);
    }

    private void setupViewPager(ViewPager viewPager) {
        viewPagerAdapter.addFragment(new RestaurantsFragment(), Utilities.RESTAURANTS);
        viewPagerAdapter.addFragment(new EventsFragment(),Utilities.EVENTS);
        viewPagerAdapter.addFragment(new RestaurantsFragment(), "Three");

        viewPager.setAdapter(viewPagerAdapter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
