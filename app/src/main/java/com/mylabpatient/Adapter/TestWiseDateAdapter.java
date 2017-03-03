package com.mylabpatient.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mylabpatient.R;

import java.util.ArrayList;

/**
 * Created by Sunil on 3/2/2017.
 */

public class TestWiseDateAdapter extends RecyclerView.Adapter<TestWiseDateAdapter.MyViewHolder> {

    ArrayList<String> arraylist=new ArrayList<>();
    Context mCtx;
    public static OnItemClickListenerTaseWiseDate mOnItemClickListener;

    public TestWiseDateAdapter(Context context, ArrayList<String> mylist){
        this.mCtx=context;
        this.arraylist=mylist;
    }

    public interface OnItemClickListenerTaseWiseDate {
        void onItemClickTaseWiseDate(View view,int position);
    }
    public void setOnItemClickListener(OnItemClickListenerTaseWiseDate onItemClickListener){
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemview= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.testlist_items_datewise,parent,false);

        return new MyViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.txttestDate.setText(arraylist.get(position));
    }

    @Override
    public int getItemCount() {
        return arraylist.size();
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
            if(mOnItemClickListener != null ){
                mOnItemClickListener.onItemClickTaseWiseDate(itemView,getLayoutPosition());
            } else{
                Log.e("Null","Null");
            }
        }
    }
}
