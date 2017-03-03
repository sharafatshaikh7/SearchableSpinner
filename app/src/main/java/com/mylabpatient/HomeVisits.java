package com.mylabpatient;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.mylabpatient.Fragments.AddHomeVisitsFrag;
import com.mylabpatient.Fragments.CancelHomeVisits;
import com.mylabpatient.Fragments.HomeVisitFrag;
import com.mylabpatient.Fragments.PreviousHomeVisits;
import com.mylabpatient.PagerAdapter.HomevisitsPager;
import com.mylabpatient.SharedPreference.PreferenceServices;

import java.util.HashMap;

public class HomeVisits extends AppCompatActivity {

    public static HashMap<String,String> map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_visits);

        //getting login details from shared preference
        map= PreferenceServices.getInstance(HomeVisits.this).getLoginDetails();
        Log.e("MapData",map.get(PreferenceServices.PatientId));


        try{
            getSupportActionBar().setTitle("Home Visit Details");
        }catch (Exception e){
            Log.e(getLocalClassName(),e.getMessage());
        }

        Fragment fragment=new AddHomeVisitsFrag();
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.homevisitFrameLayout,fragment);
        //fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

//        Fragment fragment=new HomeVisitFrag();
//        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
//        fragmentTransaction.add(R.id.homevisitFrameLayout,fragment);
//        fragmentTransaction.commit();

    }
    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//
//        getMenuInflater().inflate(R.menu.homevisitsmenu,menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//        int id=item.getItemId();
//        switch (id){
//            case R.id.addhomevisits:
////                Fragment fragment=new AddHomeVisitsFrag();
////                FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
////                fragmentTransaction.add(R.id.homevisitFrameLayout,fragment);
////                fragmentTransaction.addToBackStack(null);
////                fragmentTransaction.commit();
//                break;
//        }
//        return true;
//    }
}
