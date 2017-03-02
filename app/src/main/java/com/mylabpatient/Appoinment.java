package com.mylabpatient;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.mylabpatient.Fragments.AddAppoinment;
import com.mylabpatient.Fragments.AppoinmentFrag;
import com.mylabpatient.Fragments.CancelAppoinment;
import com.mylabpatient.Fragments.PreviousHomeVisits;
import com.mylabpatient.PagerAdapter.AppoinmentPager;
import com.mylabpatient.SharedPreference.PreferenceServices;

import java.util.HashMap;

public class Appoinment extends AppCompatActivity {

    private final static String APPOINMENT_LIST = "APPOINMENT_LIST";
    public static HashMap<String,String> map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appoinment);


        map= PreferenceServices.getInstance(Appoinment.this).getLoginDetails();

        try{
            getSupportActionBar().setTitle("Appoinment Details");
        }catch (Exception e){
            Log.e(getLocalClassName(),e.getMessage());
        }
        Fragment fragment=new AppoinmentFrag();
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.appoinmentFrameLayout,fragment);
        //fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.appoinmentmenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id=item.getItemId();
        switch (id){

            case R.id.addapp:
                Fragment fragment=new AddAppoinment();
                FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
                fragmentTransaction.add(R.id.appoinmentFrameLayout,fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                break;
        }
        return true;
    }
}
