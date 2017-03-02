package com.mylabpatient.PagerAdapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.mylabpatient.Fragments.CancelAppoinment;
import com.mylabpatient.Fragments.PreviousAppoinment;

import java.util.ArrayList;

/**
 * Created by Sunil on 2/24/2017.
 */

public class AppoinmentPager extends FragmentPagerAdapter {

    ArrayList<Fragment> fragments=new ArrayList<>();
    ArrayList<String> titles=new ArrayList<>();
    public AppoinmentPager(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0: return new PreviousAppoinment();
            case 1: return new CancelAppoinment();
        }
        return null;
    }

    public void add(Fragment fragment,String title){
        fragments.add(fragment);
        titles.add(title);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {

        switch (position){
            case 0: return "Previous Appoinment";
            case 1: return "Cancel Appoinment";
        }
        return null;
    }
}
