package com.searchablespinner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

public class MainActivity extends AppCompatActivity {

    Spinner spiCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spiCity=(com.toptoche.searchablespinnerlibrary.SearchableSpinner)findViewById(R.id.mainspinner);

        String City[]={"Mumbai","Delhi","Bihar","Kolkata"};

        ArrayAdapter adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,City);
        spiCity.setAdapter(adapter);


















//        ;sfafasf;asfasfsa

    }
}
