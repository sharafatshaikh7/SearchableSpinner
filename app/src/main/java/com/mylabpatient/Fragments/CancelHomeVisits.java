package com.mylabpatient.Fragments;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.mylabpatient.Adapter.CancelHomeVisitAdapter;
import com.mylabpatient.ConstantClasses.CustomeToast;
import com.mylabpatient.ConstantClasses.NetworkCheck;
import com.mylabpatient.DataSource.CancelHomeVisit;
import com.mylabpatient.DataSource.CancelReasonDataSource;
import com.mylabpatient.HomeVisits;
import com.mylabpatient.R;
import com.mylabpatient.SharedPreference.PreferenceServices;
import com.mylabpatient.WebServices.Webservices;

import org.ksoap2.serialization.SoapObject;

import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;


public class CancelHomeVisits extends Fragment implements CancelHomeVisitAdapter.OnItemClickListnerCancelVisit {

    RecyclerView recyclerView;
    Context mCtx;
    public static final String TAG="CancelHomeVisits";
    ArrayList<CancelHomeVisit> array_CancelHomeVisit=new ArrayList<>();
    ArrayList<CancelReasonDataSource> arrayList_CancelReason=new ArrayList<>();
    ArrayList<String> resons=new ArrayList<>();
    CancelHomeVisitAdapter cancelHomeVisitAdapter;
    NetworkCheck networkCheck;
    SweetAlertDialog progressDialog;
    String PatientId;
    Dialog reasonCancelDialog;
    CustomeToast customeToast=new CustomeToast();
    String AppoinmentId,Contact,patientName,VisitDate,VisitTime,Labname,LabCode,SendReason;

    public CancelHomeVisits() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view= inflater.inflate(R.layout.fragment_cancel_home_visits, container, false);
        //initalizing the widget in this class
        init(view);

        //initializing the progress dialog
        progressDialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.PROGRESS_TYPE);
        progressDialog.getProgressHelper().setBarColor(Color.parseColor("#EF6C00"));
        progressDialog.setTitleText("Loading");
        progressDialog.setCancelable(false);

        //intializing the network constant class
        networkCheck=new NetworkCheck(mCtx);

        if(networkCheck.isConnectinToInternet()){
            new CancelHomeVisitsAsync().execute();
        }else{
            customeToast.CustomeToastSetting(mCtx,"Internet Connection Required");
        }

        //initializing the adapter
        cancelHomeVisitAdapter=new CancelHomeVisitAdapter(mCtx,array_CancelHomeVisit);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(mCtx);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(cancelHomeVisitAdapter);
        cancelHomeVisitAdapter.setOnItemClickListnerCancelVisit(this);

        return view;
    }

    public void init(View view){

        PatientId= HomeVisits.map.get(PreferenceServices.PatientId);
        Log.e("PatientId",PatientId);

        recyclerView=(RecyclerView)view.findViewById(R.id.listviewCancelHomeVisit);
    }
    private void SettingReasonDialog(){
        reasonCancelDialog=new Dialog(mCtx);
        reasonCancelDialog.setContentView(R.layout.reasondialog);
        reasonCancelDialog.setCancelable(false);
        reasonCancelDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        reasonCancelDialog.setCanceledOnTouchOutside(false);

        final Spinner spireason=(Spinner) reasonCancelDialog.findViewById(R.id.spi_reasonCancel);
        ArrayAdapter reasonAdapter=new ArrayAdapter(mCtx,android.R.layout.simple_list_item_1,resons);
        spireason.setAdapter(reasonAdapter);
        Button btnSubmit=(Button)reasonCancelDialog.findViewById(R.id.btnReasonSubmit);
        ImageView imageClose=(ImageView)reasonCancelDialog.findViewById(R.id.reasonCloseWindow);

        imageClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reasonCancelDialog.dismiss();
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(spireason.getSelectedItemPosition() == 0){
                    customeToast.CustomeToastSetting(mCtx,"Please Select Reason");
                }else{
                    SendReason=spireason.getSelectedItem().toString();

                    if(networkCheck.isConnectinToInternet()){
                        //for cancel home visit
                        new CancelHomeVisitAsyn().execute();
                        //for sending massege
                        new CancelHomeVisitSendMsg().execute();

                        //after again call the cancel home visit method
                        new CancelHomeVisitsAsync().execute();

                    }else{
                        customeToast.CustomeToastSetting(mCtx,"Need Internet Connection");
                    }
                }
            }
        });

        reasonCancelDialog.show();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCtx=getActivity();
    }

    @Override
    public void onItemClickCancelHomeVisit(View view, int position) {

        if(networkCheck.isConnectinToInternet()){
            //to get the Reason Dialog
            new CancelReasonAsyn().execute();
        }else{
            customeToast.CustomeToastSetting(mCtx,"InterNet Connection Requared");
        }

        CancelHomeVisit cancelHomeVisit=array_CancelHomeVisit.get(position);
        AppoinmentId=cancelHomeVisit.getId();
        Contact=cancelHomeVisit.getContact();
        patientName=cancelHomeVisit.getPatient_Name();
        VisitDate=cancelHomeVisit.getDate();
        VisitTime=cancelHomeVisit.getTime();
        Labname=cancelHomeVisit.getPrefered_Lab();
        LabCode=cancelHomeVisit.getLab_Code();
    }

    class CancelHomeVisitsAsync extends AsyncTask<Void,Void,SoapObject>{
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
            //directly pass the mohammad login id details
            SoapObject soapObject=webservices.CancelHomeVisit(PatientId);
            Log.e("PatientId",PatientId);
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
                    Log.e("Soap",soap.getPropertyCount() +"  "+ soap.toString() );

                    SoapObject res = (SoapObject) soap.getProperty(0);
                    System.out.println("res : : "+ res.getPropertyCount() +"  "+ res.toString() );
                    Log.e("res",res.getPropertyCount() +"  "+ res.toString() );

                    SoapObject res2 = (SoapObject) soap.getProperty(1);
                    System.out.println("res2 : : "+ res2.getPropertyCount() +"  "+ res2.toString() );
                    Log.e("res2",res2.getPropertyCount() +"  "+ res2.toString() );

                    SoapObject last = (SoapObject) res2.getProperty(0);
                    System.out.println("last : : "+ last.getPropertyCount() +"  "+ last.toString() );
                    Log.e("Soap",last.getPropertyCount() +"  "+ last.toString() );

                    int count=last.getPropertyCount();
                    Log.e("CancelVisit Count : ",String.valueOf(count));

                    array_CancelHomeVisit.clear();
                    for(int i=0; i < last.getPropertyCount(); i++) {
                        //when data was increase last property was increase that y getting in the for loops
                        SoapObject last2= (SoapObject) last.getProperty(i);

                        //String Status1=last2.getProperty("Status1").toString();
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

                        array_CancelHomeVisit.add(0,new CancelHomeVisit(ID,PATIENT_ID,Patient_name,DATE,TIME,CONTACT,AREA,ADDRESS,PREFERED_LAB,
                                REMARK,FILENAME,FILEPATH,BOOKED_FLAG,Current_date,Cancel,Status,PatientContact,PatientEmail,LabCode,
                                HomevisitId,RowSent));
                        recyclerView.setAdapter(cancelHomeVisitAdapter);
                    }
                    cancelHomeVisitAdapter.notifyDataSetChanged();
                }
            }catch (Exception e){
                Log.e(TAG,e.getMessage());
            }

        }
    }
    class CancelReasonAsyn extends AsyncTask<Void,Void,SoapObject>{

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
            SoapObject soapObject=webservices.reasoncancel();
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
                    Log.e("Soap",soap.getPropertyCount() +"  "+ soap.toString() );

                    SoapObject res = (SoapObject) soap.getProperty(0);
                    System.out.println("res : : "+ res.getPropertyCount() +"  "+ res.toString() );
                    Log.e("res",res.getPropertyCount() +"  "+ res.toString() );

                    SoapObject res2 = (SoapObject) soap.getProperty(1);
                    System.out.println("res2 : : "+ res2.getPropertyCount() +"  "+ res2.toString() );
                    Log.e("res2",res2.getPropertyCount() +"  "+ res2.toString() );

                    SoapObject last = (SoapObject) res2.getProperty(0);
                    System.out.println("last : : "+ last.getPropertyCount() +"  "+ last.toString() );
                    Log.e("Soap",last.getPropertyCount() +"  "+ last.toString() );

                    int count=last.getPropertyCount();
                    Log.e("CancelVisit Count : ",String.valueOf(count));

                    arrayList_CancelReason.clear();
                    resons.clear();
                    resons.add("Select Reason");
                   // arrayList_CancelReason.add("Select Reason");
                    for(int i=0; i < last.getPropertyCount(); i++) {
                        //when data was increase last property was increase that y getting in the for loops
                        SoapObject last2= (SoapObject) last.getProperty(i);

                        //String Status1=last2.getProperty("Status1").toString();
                        String Reason=last2.getProperty("Reason").toString();
                        String id=last2.getProperty("int").toString();
                        String Reason1=last2.getProperty("Reason1").toString();

                        arrayList_CancelReason.add(new CancelReasonDataSource(Reason,id,Reason1));
                        resons.add(Reason);
                    }

                    if(count > 0){
                        SettingReasonDialog();
                    }else{

                    }
                }
            }catch (Exception e){
                Log.e(TAG,e.getMessage());
            }

        }
    }
    class CancelHomeVisitAsyn extends AsyncTask<Void,Void,SoapObject>{

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
            SoapObject soapObject=webservices.Cancle_HomeVisit_P(AppoinmentId,SendReason);
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
                }
            }catch (Exception e){
                Log.e(TAG,e.getMessage());
            }

        }
    }
    class CancelHomeVisitSendMsg extends AsyncTask<Void,Void,SoapObject>{

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
            SoapObject soapObject=webservices.Homevisitcancelmsg(AppoinmentId,Contact,patientName,VisitDate,VisitTime,Labname,LabCode);
            return soapObject;
        }

        @Override
        protected void onPostExecute(SoapObject soapObject) {
            super.onPostExecute(soapObject);
            try{
                if(progressDialog != null && progressDialog.isShowing())
                    progressDialog.dismiss();

                if(reasonCancelDialog != null && reasonCancelDialog.isShowing())
                reasonCancelDialog.dismiss();

                customeToast.CustomeToastSetting(mCtx,"Home Visit Cancel Succesfully");

            }catch (Exception e){
                Log.e(TAG,e.getMessage());
            }
            try{
                if(soapObject == null){
                    Log.e(TAG,soapObject.toString());
                }else{
                    Log.e(TAG,soapObject.toString());
                }
            }catch (Exception e){
                Log.e(TAG,e.getMessage());
            }

        }
    }
}
