package com.mylabpatient.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.mylabpatient.DataSource.CancelAppoinmentData;
import com.mylabpatient.R;

import java.util.ArrayList;

/**
 * Created by Sunil on 2/28/2017.
 */

public class CancelAppoinmentAdapter extends RecyclerView.Adapter<CancelAppoinmentAdapter.MyViewHolder> {

    Context mCtx;
    ArrayList<CancelAppoinmentData> cancelAppoinmentDataArrayList=new ArrayList<>();

    public CancelAppoinmentAdapter(Context context, ArrayList<CancelAppoinmentData> mylist){
        mCtx=context;
        cancelAppoinmentDataArrayList=mylist;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view=LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cancelappoinment,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        CancelAppoinmentData cancelAppoinmentData=cancelAppoinmentDataArrayList.get(position);

        holder.txtDate.setText(cancelAppoinmentData.getDATE());
        holder.txtTime.setText(cancelAppoinmentData.getTIME());
        holder.txtPreferedLab.setText(cancelAppoinmentData.getPREFERED_LAB());
        holder.txtStatus.setText(cancelAppoinmentData.getStatus());

        if(cancelAppoinmentData.getStatus().trim().equals("EXPIRE"))
            holder.txtStatus.setTextColor(Color.parseColor("#EF6C00"));
        else
            holder.txtStatus.setTextColor(Color.parseColor("#29B6F6"));
    }

    @Override
    public int getItemCount() {
        return cancelAppoinmentDataArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView txtDate,txtTime,txtPreferedLab,txtStatus;
        Button btn_cancelHomeVisit;
        public MyViewHolder(View itemView) {
            super(itemView);

            txtDate=(TextView)itemView.findViewById(R.id.cancelAppoinmentDate);
            txtTime=(TextView)itemView.findViewById(R.id.cancelAppoinmentTime);
            txtPreferedLab=(TextView)itemView.findViewById(R.id.cancelAppoinmentPreferLab);
            txtStatus=(TextView)itemView.findViewById(R.id.cancelAppoinmentstatus);
            btn_cancelHomeVisit=(Button)itemView.findViewById(R.id.cancelAppoinmentBtnCancel);
        }
    }
}
