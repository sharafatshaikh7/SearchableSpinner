package com.mylabpatient.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.mylabpatient.DataSource.PreviouHomeVisits;
import com.mylabpatient.R;

import java.util.ArrayList;

/**
 * Created by Sunil on 2/25/2017.
 */

public class BookedHomeVisitAdapter extends RecyclerView.Adapter<BookedHomeVisitAdapter.MyViewHolder>{

    private Context mCtx;
    private ArrayList<PreviouHomeVisits> previouslist;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txtName,txtDate,txtTime,txtRefernce,txtContact,txtArea,
                txtAddress,txtPreferLab,txtFileName,txtStatus;
        Button btnsubmit,btnCancel;
        public MyViewHolder(View view) {
            super(view);

            txtName=(TextView)view.findViewById(R.id.txtbookhomevisitlistName);
            txtDate=(TextView)view.findViewById(R.id.txtbookhomevisitlistDate);
            txtTime=(TextView)view.findViewById(R.id.txtbookhomevisitlistTime);
            txtRefernce=(TextView)view.findViewById(R.id.txtbookhomevisitlistReferNum);
            txtContact=(TextView)view.findViewById(R.id.boohomevisitlistContact);
            txtArea=(TextView)view.findViewById(R.id.boohomevisitlistare);
            txtAddress=(TextView)view.findViewById(R.id.boohomevisitlistAddress);
            txtPreferLab=(TextView)view.findViewById(R.id.boohomevisitlistPrefer);
            txtFileName=(TextView)view.findViewById(R.id.boohomevisitlistFileName);
            txtStatus=(TextView)view.findViewById(R.id.boohomevisitliststatus);
            btnCancel=(Button)view.findViewById(R.id.boohomevisitlistbtncancel);
            btnsubmit=(Button)view.findViewById(R.id.boohomevisitlistbtnbook);

        }
    }

    public BookedHomeVisitAdapter(Context context, ArrayList<PreviouHomeVisits> mylist){
        mCtx=context;
        previouslist=mylist;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.previoushomevisiti_tem,parent,false);

        return new MyViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        PreviouHomeVisits previouHomeVisits=previouslist.get(position);

        holder.txtName.setText(previouHomeVisits.getPatient_Name());
        holder.txtDate.setText(previouHomeVisits.getDate());
        holder.txtTime.setText(previouHomeVisits.getTime());
        holder.txtRefernce.setText(previouHomeVisits.getHomevisit_Id());
        holder.txtContact.setText(previouHomeVisits.getContact());
        holder.txtArea.setText(previouHomeVisits.getArea());
        holder.txtAddress.setText(previouHomeVisits.getAddress());
        holder.txtPreferLab.setText(previouHomeVisits.getPrefered_Lab());

        holder.txtStatus.setText(previouHomeVisits.getStatus1());


        if(previouHomeVisits.getFile_Name().toString().contains(".pdf") ||
                previouHomeVisits.getFile_Name().toString().contains(".jpg"))
        {
            holder.txtFileName.setText(previouHomeVisits.getFile_Name());
        }else{
            holder.txtFileName.setText("No Attached File");
        }

        if(previouHomeVisits.getStatus1().trim().equals("EXPIRE"))
            holder.txtStatus.setTextColor(Color.parseColor("#EF6C00"));
        else
            holder.txtStatus.setTextColor(Color.parseColor("#29B6F6"));

        holder.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mCtx,"Canel : "+position,Toast.LENGTH_SHORT).show();
            }
        });

        holder.btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mCtx,"submit : "+position,Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return previouslist.size();
    }
}
