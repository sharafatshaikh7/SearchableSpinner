package com.mylabpatient.Fragments;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mylabpatient.Adapter.CancelAppoinmentAdapter;
import com.mylabpatient.Adapter.CancelHomeVisitAdapter;
import com.mylabpatient.Appoinment;
import com.mylabpatient.ConstantClasses.CustomeToast;
import com.mylabpatient.ConstantClasses.NetworkCheck;
import com.mylabpatient.DataSource.CancelAppoinmentData;
import com.mylabpatient.DataSource.CancelHomeVisit;
import com.mylabpatient.R;
import com.mylabpatient.SharedPreference.PreferenceServices;
import com.mylabpatient.WebServices.Webservices;

import org.ksoap2.serialization.SoapObject;

import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;


public class CancelAppoinment extends Fragment {

    RecyclerView recyclerView;
    Context mCtx;
    public static final String TAG="CancelAppoinment";
    NetworkCheck networkCheck;
    SweetAlertDialog progressDialog;
    CustomeToast customeToast=new CustomeToast();
    ArrayList<CancelAppoinmentData> arrayList_CancelAppoinment=new ArrayList<>();
    String PatientId;
    CancelAppoinmentAdapter cancelAppoinmentAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view= inflater.inflate(R.layout.fragment_cancel_appoinment, container, false);

        //initializing the wisget here
        init(view);

        //initializing the appoinemtn
        progressDialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.PROGRESS_TYPE);
        progressDialog.getProgressHelper().setBarColor(Color.parseColor("#EF6C00"));
        progressDialog.setTitleText("Loading");
        progressDialog.setCancelable(false);

        //initializing the adapter here
        cancelAppoinmentAdapter=new CancelAppoinmentAdapter(mCtx,arrayList_CancelAppoinment);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(mCtx);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(cancelAppoinmentAdapter);

        //initializing the network class here
        networkCheck=new NetworkCheck(mCtx);
        if(networkCheck.isConnectinToInternet()){
            new CancelAppoinmentAsyn().execute();
        }else{
            customeToast.CustomeToastSetting(mCtx,"Internet Connection Required");
        }
        return view;
    }
    private void init(View view){
        recyclerView=(RecyclerView)view.findViewById(R.id.listviewCancelAppoinment);

        PatientId= Appoinment.map.get(PreferenceServices.PatientId);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCtx=getActivity();
    }
    class CancelAppoinmentAsyn extends AsyncTask<Void,Void,SoapObject>{

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
            SoapObject soapObject=webservices.CancelAppoinment(PatientId);
            return soapObject;
        }

        @Override
        protected void onPostExecute(SoapObject soapObject) {
            super.onPostExecute(soapObject);
            try{
                if(progressDialog != null && progressDialog.isShowing())
                    progressDialog.dismiss();

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


                    for(int i=0; i < last.getPropertyCount(); i++) {
                        //when data was increase last property was increase that y getting in the for loops
                        SoapObject last2= (SoapObject) last.getProperty(i);

                        String Booked_By,CancelReason;

                        String ID=last2.getProperty("ID").toString();
                        String PATIENT_ID=last2.getProperty("USERNAME_Book").toString();
                        String Patient_name=last2.getProperty("PATIENT_ID").toString();
                        String DATE=last2.getProperty("DATE").toString();
                        String TIME=last2.getProperty("TIME").toString();
                        String CONTACT=last2.getProperty("CONTACT").toString();
                        String AREA=last2.getProperty("AREA").toString();
                        String ADDRESS=last2.getProperty("ADDRESS").toString();
                        String PREFERED_LAB=last2.getProperty("PREFERED_LAB").toString();
                        String REMARK=last2.getProperty("REMARK").toString();
                        String BOOKED_FLAG=last2.getProperty("FLAG").toString();
                        String FILENAME=last2.getProperty("FILENAME").toString();
                        String FILEPATH=last2.getProperty("FILEPATH").toString();
                        String Cancel=last2.getProperty("Cancel").toString();
                        String Status=last2.getProperty("Status").toString();
                        //some time this value not come that y put in the try catch
                        try{
                            Booked_By=last2.getProperty("BookedBy").toString();
                            CancelReason=last2.getProperty("CancleReason").toString();
                        }catch (Exception e){
                            Log.e(TAG,e.getMessage());
                            Booked_By="";
                            CancelReason="";
                        }
                        String PatientContact=last2.getProperty("PatientContact").toString();
                        String PatientEmail=last2.getProperty("PatientEmail").toString();
                        String AppoinmentId=last2.getProperty("AppointmentID").toString();
                        String age=last2.getProperty("age").toString();
                        String LabCode=last2.getProperty("LabCode").toString();

                        String RowSent=last2.getProperty("RowSent").toString();

                        arrayList_CancelAppoinment.add(0,new CancelAppoinmentData(ID,PATIENT_ID,Patient_name,DATE,TIME,CONTACT,AREA,ADDRESS,
                                PREFERED_LAB,REMARK,BOOKED_FLAG,FILENAME,FILEPATH,Cancel,Status,Booked_By,PatientContact,PatientEmail,
                                AppoinmentId,age,LabCode,CancelReason,RowSent));

                        recyclerView.setAdapter(cancelAppoinmentAdapter);
                    }
                }

            }catch (Exception e){
                Log.e(TAG,e.getMessage());
            }

        }
    }
}
