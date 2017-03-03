package com.mylabpatient.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mylabpatient.DataSource.DateWiseTestDataSource;
import com.mylabpatient.DataSource.ReportTestWiseDataSource;
import com.mylabpatient.R;

import java.util.ArrayList;

/**
 * Created by Sunil on 3/2/2017.
 */

public class DatewiseTestAdapter extends RecyclerView.Adapter<DatewiseTestAdapter.MyViewHolder>{

    ArrayList<DateWiseTestDataSource> arrayListTestWise=new ArrayList<>();
    Context mCtx;
    public static OnTestClickListner mOnTestClickListner;

    public DatewiseTestAdapter(Context context, ArrayList<DateWiseTestDataSource> mylist){
        this.mCtx=context;
        this.arrayListTestWise=mylist;
    }

    public interface OnTestClickListner {
        void onTestClick(View view, int position);
    }
    public void setOnTestClickListner(OnTestClickListner onItemClickListener){
        mOnTestClickListner = onItemClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemview= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.testlist_items_testwise,parent,false);

        return new MyViewHolder(itemview);
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        DateWiseTestDataSource dateWiseTestDataSource=arrayListTestWise.get(position);

        holder.txttestId.setText(dateWiseTestDataSource.getTestId());
        holder.txtTestName.setText(dateWiseTestDataSource.getTestName());
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

            if(mOnTestClickListner != null ){
                mOnTestClickListner.onTestClick(itemView,getLayoutPosition());
            } else{
                Log.e("Null","Null");
            }
        }

    }
}
