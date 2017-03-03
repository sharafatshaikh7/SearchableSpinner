package com.mylabpatient.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mylabpatient.DataSource.FinalTestDetailsDataSource;
import com.mylabpatient.R;

import java.util.ArrayList;

/**
 * Created by Sunil on 3/3/2017.
 */

public class FinalTestDetailsAdapter extends RecyclerView.Adapter<FinalTestDetailsAdapter.MyViewHolder>  {

    ArrayList<FinalTestDetailsDataSource> arrayList_FinalTestDetails=new ArrayList<>();
    Context mCtx;

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemview= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.testdetailsdialog,parent,false);

        return new MyViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        FinalTestDetailsDataSource finalTestDetailsDataSource=arrayList_FinalTestDetails.get(position);

       // holder.txtTitle.setText(finalTestDetailsDataSource.getTitleName());
        holder.txtcentername.setText(finalTestDetailsDataSource.getCenterName());
        holder.txtdocname.setText(finalTestDetailsDataSource.getDocName());
        holder.txttestname.setText(finalTestDetailsDataSource.getTitleName());
        holder.txtnormalvalue.setText(finalTestDetailsDataSource.getNormalValue());
        holder.txtmaxvalue.setText(finalTestDetailsDataSource.getMAxValue());
        holder.txtminvalue.setText(finalTestDetailsDataSource.getMinValue());
        holder.txtFildValue.setText(finalTestDetailsDataSource.getFildValue());
        holder.txtunit.setText(finalTestDetailsDataSource.getTestUnit());
    }

    public FinalTestDetailsAdapter(Context context,ArrayList<FinalTestDetailsDataSource> mylist){
        this.mCtx=context;
        this.arrayList_FinalTestDetails=mylist;
    }

    @Override
    public int getItemCount() {
        return arrayList_FinalTestDetails.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView txtTitle,txttestname,txtnormalvalue,txtmaxvalue,txtminvalue,txtdocname,
                txtcentername,txtFildValue,txtunit;

        public MyViewHolder(View itemView) {
            super(itemView);

           // txtTitle=(TextView) itemView.findViewById(R.id.txtTestReportHeading);
            txtcentername=(TextView) itemView.findViewById(R.id.txtTestDetailsCenterName);
            txtdocname=(TextView) itemView.findViewById(R.id.txtTestDetailsdoctoreName);
            txttestname=(TextView)itemView.findViewById(R.id.txtTestDetailsTestName) ;
            txtnormalvalue=(TextView) itemView.findViewById(R.id.txtTestDetailsNormalRange);
            txtmaxvalue=(TextView) itemView.findViewById(R.id.txtTestDetailsMaxRange);
            txtminvalue=(TextView) itemView.findViewById(R.id.txtTestDetailsMinRange);
            txtFildValue=(TextView) itemView.findViewById(R.id.txtTestDetailsFild);
            txtunit=(TextView) itemView.findViewById(R.id.txtTestDetailsResult);
        }
    }
}
