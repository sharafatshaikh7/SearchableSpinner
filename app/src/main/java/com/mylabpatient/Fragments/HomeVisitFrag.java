package com.mylabpatient.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.mylabpatient.PagerAdapter.HomevisitsPager;
import com.mylabpatient.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeVisitFrag extends Fragment {
    TabLayout tabLayout;
    ViewPager viewPager;
    HomevisitsPager homevisitsPager;
    Context mCtx;


    public HomeVisitFrag() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view= inflater.inflate(R.layout.fragment_home_visit, container, false);

        //initializing and adding the tab
        tabLayout= (TabLayout)view.findViewById(R.id.homevistsTabLayout);
        viewPager=(ViewPager)view.findViewById(R.id.homevisitsViewPager);
        homevisitsPager=new HomevisitsPager(getActivity().getSupportFragmentManager());
        homevisitsPager.add(new PreviousHomeVisits(),"Previous Home Visits");
        homevisitsPager.add(new CancelHomeVisits(),"Cancel Home Visits");

        viewPager.setAdapter(homevisitsPager);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(1);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCtx=getActivity();
    }
}
