package com.mylabpatient.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mylabpatient.DataSource.ReportDateWiseDataSource;
import com.mylabpatient.R;

import java.util.ArrayList;

/**
 * Created by Sunil on 3/2/2017.
 */

public class TestReportDatetWiseAdapter extends RecyclerView.Adapter<TestReportDatetWiseAdapter.MyViewHolder> {

    ArrayList<ReportDateWiseDataSource> arrayListDateWise=new ArrayList<>();
    Context mCtx;

    public TestReportDatetWiseAdapter(Context context, ArrayList<ReportDateWiseDataSource> mylist){
        this.mCtx=context;
        this.arrayListDateWise=mylist;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemview= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.testlist_items_datewise,parent,false);

        return new MyViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ReportDateWiseDataSource reportTestWiseDataSource=arrayListDateWise.get(position);
        holder.txttestDate.setText(reportTestWiseDataSource.getDate());
    }

    @Override
    public int getItemCount() {
        return arrayListDateWise.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView txttestDate;
        public MyViewHolder(View itemView) {
            super(itemView);
            txttestDate=(TextView)itemView.findViewById(R.id.txttestDate_DateWise);
        }
    }
}
