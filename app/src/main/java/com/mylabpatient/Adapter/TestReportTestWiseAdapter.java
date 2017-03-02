package com.mylabpatient.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mylabpatient.DataSource.ReportTestWiseDataSource;
import com.mylabpatient.R;
import com.mylabpatient.Reports;
import com.mylabpatient.WebServices.Webservices;

import org.ksoap2.serialization.SoapObject;

import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by Sunil on 3/2/2017.
 */

public class TestReportTestWiseAdapter extends RecyclerView.Adapter<TestReportTestWiseAdapter.MyViewHolder>{

    ArrayList<ReportTestWiseDataSource> arrayListTestWise=new ArrayList<>();
    Context mCtx;
    public static int SettingData;

    public TestReportTestWiseAdapter(Context context,ArrayList<ReportTestWiseDataSource> mylist){
        this.mCtx=context;
        this.arrayListTestWise=mylist;
    }

    public static int getSettingData() {
        return SettingData;
    }

    public static void setSettingData(int settingData) {
        SettingData = settingData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemview= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.testlist_items_testwise,parent,false);
        return new MyViewHolder(itemview);
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        ReportTestWiseDataSource reportTestWiseDataSource=arrayListTestWise.get(position);

        holder.txttestId.setText(reportTestWiseDataSource.getTestId());
        holder.txtTestName.setText(reportTestWiseDataSource.getTestname());
    }

    @Override
    public int getItemCount() {
        return arrayListTestWise.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{

        public TextView txttestId,txtTestName;
        public MyViewHolder(View itemView) {
            super(itemView);
            txttestId=(TextView)itemView.findViewById(R.id.txttestId_TestWise);
            txtTestName=(TextView)itemView.findViewById(R.id.txttestName_TestWise);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Log.e("onClick", String.valueOf(getLayoutPosition())
                    + "\n" + String.valueOf(txtTestName.getText()));
            setSettingData(getLayoutPosition());
        }
    }
}
