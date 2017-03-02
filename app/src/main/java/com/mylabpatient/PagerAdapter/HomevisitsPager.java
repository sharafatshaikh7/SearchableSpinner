package com.mylabpatient.PagerAdapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.mylabpatient.Fragments.CancelHomeVisits;
import com.mylabpatient.Fragments.PreviousHomeVisits;

import java.util.ArrayList;

/**
 * Created by Sunil on 2/24/2017.
 */

public class HomevisitsPager extends FragmentPagerAdapter {
    ArrayList<Fragment> fragments=new ArrayList<>();
    ArrayList<String> titles=new ArrayList<>();

    public HomevisitsPager(FragmentManager fm) {
        super(fm);
    }

    public void add(Fragment fragment,String title){
        fragments.add(fragment);
        titles.add(title);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0: return new PreviousHomeVisits();
            case 1: return new CancelHomeVisits();
        }
        return null;
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {

        switch (position){
            case 0: return "Previous Home Visits";
            case 1: return "Cancel Home Visits";
        }
        return null;
    }
}
