package com.mylabpatient.Fragments;


import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mylabpatient.Adapter.BookedHomeVisitAdapter;
import com.mylabpatient.ConstantClasses.CustomeToast;
import com.mylabpatient.ConstantClasses.NetworkCheck;
import com.mylabpatient.DataSource.PreviouHomeVisits;
import com.mylabpatient.HomeVisits;
import com.mylabpatient.R;
import com.mylabpatient.SharedPreference.PreferenceServices;
import com.mylabpatient.WebServices.Webservices;
import org.ksoap2.serialization.SoapObject;

import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;


public class PreviousHomeVisits extends Fragment {

    RecyclerView recyclerView;
    SweetAlertDialog progressDialog;
    Context mCtx;
    public final static String TAG="BOOKED_HOMEVISIT_LIST";
    ArrayList<PreviouHomeVisits> array_PreviouHomeVisits=new ArrayList<>();
    BookedHomeVisitAdapter bookedHomeVisitAdapter;
    NetworkCheck networkCheck;
    String ContactNumber;
    CustomeToast customeToast=new CustomeToast();

    public PreviousHomeVisits() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view= inflater.inflate(R.layout.fragment_blank, container, false);

        //calling the initialilizing method
        init(view);

        //initializing the progress dialog
        progressDialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.PROGRESS_TYPE);
        progressDialog.getProgressHelper().setBarColor(Color.parseColor("#EF6C00"));
        progressDialog.setTitleText("Loading");
        progressDialog.setCancelable(false);


        //initializing the adapter class
        bookedHomeVisitAdapter=new BookedHomeVisitAdapter(mCtx,array_PreviouHomeVisits);

        //setting on
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(mCtx);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(bookedHomeVisitAdapter);


        networkCheck=new NetworkCheck(mCtx);
        if(networkCheck.isConnectinToInternet()){
            new BOOKED_HOMEVISIT_LIST().execute();
        }else{
            customeToast.CustomeToastSetting(mCtx,"Internet Connection Required");
        }

        return view;
    }

    private void init(View view){

        ContactNumber= HomeVisits.map.get(PreferenceServices.Contact);
        recyclerView=(RecyclerView)view.findViewById(R.id.listviewPreviousHomeVisit);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCtx=getActivity();
    }

    class BOOKED_HOMEVISIT_LIST extends AsyncTask<Void,Void,SoapObject>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            try{
                if(progressDialog != null && !progressDialog.isShowing())
                    progressDialog.show();
            }catch (Exception e){
                Log.e(TAG,e.getMessage());
            }

        }

        @Override
        protected SoapObject doInBackground(Void... params) {

            Webservices webservices=new Webservices();
            SoapObject soapObject= webservices.PreviousHomeVisit(ContactNumber);
            return soapObject;
        }

        @Override
        protected void onPostExecute(SoapObject soapObject) {
            super.onPostExecute(soapObject);
            try{
                if(progressDialog != null && progressDialog.isShowing())
                    progressDialog.dismiss();
            }catch (Exception e){
                Log.e(TAG,e.getMessage());
            }
            try{
                if(soapObject == null){
                    Log.e(TAG,soapObject.toString());
                }else{
                    Log.e(TAG,soapObject.toString());


                    SoapObject soap = (SoapObject) soapObject.getProperty(0);
                    System.out.println("soap : : "+ soap.getPropertyCount() +"  "+ soap.toString() );

                    SoapObject res = (SoapObject) soap.getProperty(0);
                    System.out.println("res : : "+ res.getPropertyCount() +"  "+ res.toString() );

                    SoapObject res2 = (SoapObject) soap.getProperty(1);
                    System.out.println("res2 : : "+ res2.getPropertyCount() +"  "+ res2.toString() );

                    SoapObject last = (SoapObject) res2.getProperty(0);
                    System.out.println("last : : "+ last.getPropertyCount() +"  "+ last.toString() );

                    int count=last.getPropertyCount();
                    Log.e("BookHomeVisit Count : ",String.valueOf(count));


                    for(int i=0; i < last.getPropertyCount(); i++) {
                        //when data was increase last property was increase that y getting in the for loops
                        SoapObject last2= (SoapObject) last.getProperty(i);

                        String Status1=last2.getProperty("Status1").toString();
                        String ID=last2.getProperty("ID").toString();
                        String PATIENT_ID=last2.getProperty("PATIENT_ID").toString();
                        String Patient_name=last2.getProperty("Patient_name").toString();
                        String DATE=last2.getProperty("DATE").toString();
                        String TIME=last2.getProperty("TIME").toString();
                        String CONTACT=last2.getProperty("CONTACT").toString();
                        String AREA=last2.getProperty("AREA").toString();
                        String ADDRESS=last2.getProperty("ADDRESS").toString();
                        String PREFERED_LAB=last2.getProperty("PREFERED_LAB").toString();
                        String REMARK=last2.getProperty("REMARK").toString();
                        String FILENAME=last2.getProperty("FILENAME").toString();
                        String FILEPATH=last2.getProperty("FILEPATH").toString();
                        String BOOKED_FLAG=last2.getProperty("BOOKED_FLAG").toString();
                        String Current_date=last2.getProperty("Current_date").toString();
                        String Cancel=last2.getProperty("Cancel").toString();
                        String Status=last2.getProperty("Status").toString();
                        String PatientContact=last2.getProperty("PatientContact").toString();
                        String PatientEmail=last2.getProperty("PatientEmail").toString();
                        String LabCode=last2.getProperty("LabCode").toString();
                        String HomevisitId=last2.getProperty("HomevisitId").toString();
                        String RowSent=last2.getProperty("RowSent").toString();

                        array_PreviouHomeVisits.add(0,new PreviouHomeVisits(Status1,ID,PATIENT_ID,Patient_name,DATE,TIME,CONTACT,AREA,
                                ADDRESS,PREFERED_LAB,REMARK,FILENAME,FILEPATH,BOOKED_FLAG,Current_date,Cancel,Status,PatientContact,
                                PatientEmail,LabCode,HomevisitId,RowSent));
                        recyclerView.setAdapter(bookedHomeVisitAdapter);
                    }
                }
            }catch (Exception e){
                Log.e(TAG,e.getMessage());
            }
        }
    }

}
