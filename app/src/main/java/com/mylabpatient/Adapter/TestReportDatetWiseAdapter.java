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
    public static onDateClick monDateClick;

    public TestReportDatetWiseAdapter(Context context, ArrayList<ReportDateWiseDataSource> mylist){
        this.mCtx=context;
        this.arrayListDateWise=mylist;
    }

    public interface onDateClick {
        void onDateClick(View view,int position);
    }
    public void setonDateClick(onDateClick onItemClickListener){
        monDateClick = onItemClickListener;
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

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView txttestDate;
        public MyViewHolder(View itemView) {
            super(itemView);
            txttestDate=(TextView)itemView.findViewById(R.id.txttestDate_DateWise);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Log.e("onClick", String.valueOf(getLayoutPosition())
                    + "\n" + String.valueOf(txttestDate.getText()));

            if(monDateClick != null ){
                monDateClick.onDateClick(itemView,getLayoutPosition());
            } else{
                Log.e("Null","Null");
            }
        }
    }
}
