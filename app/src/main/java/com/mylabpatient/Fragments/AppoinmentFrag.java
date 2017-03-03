package com.mylabpatient.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mylabpatient.PagerAdapter.AppoinmentPager;
import com.mylabpatient.R;


public class AppoinmentFrag extends Fragment {


    TabLayout tabLayout;
    ViewPager viewPager;
    AppoinmentPager appoinmentPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view= inflater.inflate(R.layout.fragment_appoinment, container, false);

        //initializing and adding the tab
        tabLayout=(TabLayout)view.findViewById(R.id.appoinmentTabLayout);
        viewPager=(ViewPager)view.findViewById(R.id.appoinmentViewPager);
        appoinmentPager=new AppoinmentPager(getActivity().getSupportFragmentManager());
        appoinmentPager.add(new PreviousHomeVisits(),"Previous Appoinment");
        appoinmentPager.add(new CancelAppoinment(),"Cancel Home Visits");
        viewPager.setAdapter(appoinmentPager);
        tabLayout.setupWithViewPager(viewPager);
        //for setting bedefault 2 tab selected 
        viewPager.setCurrentItem(1);

        return view;
    }
}
