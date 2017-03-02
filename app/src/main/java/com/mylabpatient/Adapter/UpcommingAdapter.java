package com.mylabpatient.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mylabpatient.DataSource.UpcommingEventsList;
import com.mylabpatient.R;

import java.util.ArrayList;

/**
 * Created by Sunil on 2/28/2017.
 */

public class UpcommingAdapter extends RecyclerView.Adapter<UpcommingAdapter.MyViewHolder>{

    Context mCtx;
    ArrayList<UpcommingEventsList> arrayList_UpcommingList=new ArrayList<>();

    public UpcommingAdapter(Context context,ArrayList<UpcommingEventsList> mylist){
        this.mCtx=context;
        this.arrayList_UpcommingList=mylist;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.upcomming_layout_items,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        UpcommingEventsList upcommingEventsList=arrayList_UpcommingList.get(position);

        holder.txtFromDate.setText(upcommingEventsList.getFromDate());
        holder.txtToDate.setText(upcommingEventsList.getToDate());
        holder.txtPackageName.setText(upcommingEventsList.getPackage());
        holder.txtTestName.setText(upcommingEventsList.getTestName());
        holder.txtPrice.setText(upcommingEventsList.getPrice());
    }

    @Override
    public int getItemCount() {
        return arrayList_UpcommingList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView txtFromDate,txtToDate,txtPackageName,txtTestName,txtPrice;
        public MyViewHolder(View itemView) {
            super(itemView);

            txtFromDate=(TextView)itemView.findViewById(R.id.txtupcommingFrom);
            txtToDate=(TextView)itemView.findViewById(R.id.txtupcommingTo);
            txtPackageName=(TextView)itemView.findViewById(R.id.txtUpcommingPackageName);
            txtTestName=(TextView)itemView.findViewById(R.id.txtUpcommingTestName);
            txtPrice=(TextView)itemView.findViewById(R.id.txtUpcommingPrice);
        }
    }
}
