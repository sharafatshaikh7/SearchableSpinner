package com.mylabpatient.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.mylabpatient.DataSource.CancelHomeVisit;
import com.mylabpatient.R;

import java.util.ArrayList;

/**
 * Created by Sunil on 2/25/2017.
 */

public class CancelHomeVisitAdapter extends RecyclerView.Adapter<CancelHomeVisitAdapter.MyHolder>  {

    private Context mCtx;
    private ArrayList<CancelHomeVisit> cancelHomeVisitsList=new ArrayList<>();
    public static OnItemClickListnerCancelVisit mOnItemClickListnerCancelVisit;


    class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView txtPreferrenceName,txtContact,txtDate,txtTime,txtPreferedLab,txtStatus;
        Button btn_cancelHomeVisit;
        public MyHolder(View itemView) {
            super(itemView);

            txtPreferrenceName=(TextView) itemView.findViewById(R.id.cancelhomevisitReference);
            txtContact=(TextView)itemView.findViewById(R.id.cancelhomevisitContact);
            txtDate=(TextView)itemView.findViewById(R.id.cancelhomevisitDate);
            txtTime=(TextView)itemView.findViewById(R.id.cancelhomevisitTime);
            txtPreferedLab=(TextView)itemView.findViewById(R.id.cancelhomevisitPreferLab);
            txtStatus=(TextView)itemView.findViewById(R.id.cancelhomevisitstatus);
            btn_cancelHomeVisit=(Button)itemView.findViewById(R.id.cancelhomevisitBtnCancel);
            itemView.setOnClickListener(this);
            btn_cancelHomeVisit.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(v.getId() == btn_cancelHomeVisit.getId()){
                Log.e("btn Clicked",String.valueOf(v.getId()));
                mOnItemClickListnerCancelVisit.onItemClickCancelHomeVisit(itemView,getLayoutPosition());
            }
        }
    }

    public static interface OnItemClickListnerCancelVisit{
        void onItemClickCancelHomeVisit(View view,int position);
    }

    public void setOnItemClickListnerCancelVisit(OnItemClickListnerCancelVisit onItemClickListnerCancelVisit){
        this.mOnItemClickListnerCancelVisit=onItemClickListnerCancelVisit;
    }

    public CancelHomeVisitAdapter(Context context,ArrayList<CancelHomeVisit> mylist){
        this.cancelHomeVisitsList=mylist;
        this.mCtx=context;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemview=LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cancelhomevisit_item,parent,false);

        return new MyHolder(itemview);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {

        CancelHomeVisit cancelHomeVisit=cancelHomeVisitsList.get(position);

        holder.txtPreferrenceName.setText(cancelHomeVisit.getHomevisit_Id());
        holder.txtContact.setText(cancelHomeVisit.getContact());
        holder.txtDate.setText(cancelHomeVisit.getCurrent_Date());
        holder.txtTime.setText(cancelHomeVisit.getTime());
        holder.txtPreferedLab.setText(cancelHomeVisit.getPrefered_Lab());
        holder.txtStatus.setText(cancelHomeVisit.getStatus());

        if(cancelHomeVisit.getStatus().trim().equals("EXPIRE"))
            holder.txtStatus.setTextColor(Color.parseColor("#EF6C00"));
        else
            holder.txtStatus.setTextColor(Color.parseColor("#29B6F6"));

    }

    @Override
    public int getItemCount() {
        return cancelHomeVisitsList.size();
    }
}
