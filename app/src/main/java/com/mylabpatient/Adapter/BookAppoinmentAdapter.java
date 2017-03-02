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

import com.mylabpatient.DataSource.PreviousAppoinmentDataSource;
import com.mylabpatient.R;

import java.util.ArrayList;

/**
 * Created by Sunil on 2/27/2017.
 */

public class BookAppoinmentAdapter extends RecyclerView.Adapter<BookAppoinmentAdapter.MyviewHolder>{

    Context mCtx;
    ArrayList<PreviousAppoinmentDataSource> previouAppoinment=new ArrayList<>();

    @Override
    public MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bookedappoinmentlist,parent,false);

        return new MyviewHolder(itemview);
    }

    public BookAppoinmentAdapter(Context context,ArrayList<PreviousAppoinmentDataSource> mylist){
        this.mCtx=context;
        this.previouAppoinment=mylist;
    }

    @Override
    public void onBindViewHolder(MyviewHolder holder, final int position) {

        PreviousAppoinmentDataSource previousAppoinmentDataSource =previouAppoinment.get(position);

        holder.txtName.setText(previousAppoinmentDataSource.getUsername_book());
        holder.txtDate.setText(previousAppoinmentDataSource.getDate());
        holder.txtTime.setText(previousAppoinmentDataSource.getTime());
        holder.txtRefernce.setText(previousAppoinmentDataSource.getAppoinmentId());
        holder.txtContact.setText(previousAppoinmentDataSource.getContact());
        holder.txtArea.setText(previousAppoinmentDataSource.getArea());
        holder.txtAddress.setText(previousAppoinmentDataSource.getAddress());
        holder.txtPreferLab.setText(previousAppoinmentDataSource.getPrefered_Lab());
        holder.txtStatus.setText(previousAppoinmentDataSource.getStatus1());

        if(previousAppoinmentDataSource.getFile_Name().toString().contains(".pdf") ||
                previousAppoinmentDataSource.getFile_Name().toString().contains(".jpg"))
        {
            holder.txtFileName.setText(previousAppoinmentDataSource.getFile_Name());
        }else{
            holder.txtFileName.setText("No Attached File");
        }


        if(previousAppoinmentDataSource.getStatus1().trim().equals("EXPIRE"))
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
        return previouAppoinment.size();
    }


    class MyviewHolder extends RecyclerView.ViewHolder{

        public TextView txtName,txtDate,txtTime,txtRefernce,txtContact,txtArea,
                txtAddress,txtPreferLab,txtFileName,txtStatus,txtGenerate;
        Button btnsubmit,btnCancel;
        public MyviewHolder(View view) {
            super(view);

            txtName=(TextView)view.findViewById(R.id.txtbookappoinmentName);
            txtDate=(TextView)view.findViewById(R.id.txtbookappoinmentDate);
            txtTime=(TextView)view.findViewById(R.id.txtbookappoinmentTime);
            txtRefernce=(TextView)view.findViewById(R.id.txtbookappoinmentReferNum);
            txtContact=(TextView)view.findViewById(R.id.bookappoinmentContact);
            txtArea=(TextView)view.findViewById(R.id.bookappoinmentare);
            txtAddress=(TextView)view.findViewById(R.id.bookappoinmentAddress);
            txtPreferLab=(TextView)view.findViewById(R.id.bookappoinmentPrefer);
            txtFileName=(TextView)view.findViewById(R.id.bookappoinmentFileName);
            txtStatus=(TextView)view.findViewById(R.id.bookappoinmentstatus);
            txtGenerate=(TextView)view.findViewById(R.id.txtbookappoinmentGenerate);
            btnCancel=(Button)view.findViewById(R.id.bookappoinmentbtncancel);
            btnsubmit=(Button)view.findViewById(R.id.bookappoinmentbtnbook);

        }
    }
}
