package com.mylabpatient.Fragments;


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

import com.mylabpatient.Adapter.BookAppoinmentAdapter;
import com.mylabpatient.Appoinment;
import com.mylabpatient.ConstantClasses.CustomeToast;
import com.mylabpatient.ConstantClasses.NetworkCheck;
import com.mylabpatient.DataSource.PreviousAppoinmentDataSource;
import com.mylabpatient.R;
import com.mylabpatient.SharedPreference.PreferenceServices;
import com.mylabpatient.WebServices.Webservices;

import org.ksoap2.serialization.SoapObject;

import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;


/**
 * A simple {@link Fragment} subclass.
 */
public class PreviousAppoinment extends Fragment {

    RecyclerView recyclerView;
    SweetAlertDialog progressDialog;
    Context mCtx;
    public final static String TAG="BOOKED_Appoinment_List";
    ArrayList<PreviousAppoinmentDataSource> array_previousAppoinments=new ArrayList<>();
    NetworkCheck networkCheck;
    String ContactNumber;
    BookAppoinmentAdapter bookAppoinmentAdapte;
    CustomeToast customeToast=new CustomeToast();

    public PreviousAppoinment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view= inflater.inflate(R.layout.fragment_previous_appoinment, container, false);


        //calling the initialilizing method
        init(view);

        //initializing the progress dialog
        progressDialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.PROGRESS_TYPE);
        progressDialog.getProgressHelper().setBarColor(Color.parseColor("#EF6C00"));
        progressDialog.setTitleText("Loading");
        progressDialog.setCancelable(false);


        //initializing the adapter class
        bookAppoinmentAdapte=new BookAppoinmentAdapter(mCtx,array_previousAppoinments);

        //setting on
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(mCtx);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(bookAppoinmentAdapte);


        networkCheck=new NetworkCheck(mCtx);
        if(networkCheck.isConnectinToInternet()){
            new BOOKED_APPOINMENT().execute();
        }else{
            customeToast.CustomeToastSetting(mCtx,"Internet Connection Required");
        }

        return view;
    }

    private void init(View view){

        ContactNumber= Appoinment.map.get(PreferenceServices.Contact);
        recyclerView=(RecyclerView)view.findViewById(R.id.listviewPreviousAppoinment);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCtx=getActivity();
    }

    class BOOKED_APPOINMENT extends AsyncTask<Void,Void,SoapObject> {

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
            SoapObject soapObject= webservices.PreviousAppoinment(ContactNumber);
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
                        String Id=last2.getProperty("ID").toString();
                        String username_book=last2.getProperty("USERNAME_Book").toString();
                        String patient_id=last2.getProperty("PATIENT_ID").toString();
                        String Date=last2.getProperty("DATE").toString();
                        String Time=last2.getProperty("TIME").toString();
                        String Contact=last2.getProperty("CONTACT").toString();
                        String Area=last2.getProperty("AREA").toString();
                        String Address=last2.getProperty("ADDRESS").toString();
                        String Prefered_Lab=last2.getProperty("PREFERED_LAB").toString();
                        String Remark=last2.getProperty("REMARK").toString();
                        String Flag=last2.getProperty("FLAG").toString();
                        String File_Name=last2.getProperty("FILENAME").toString();
                        String File_Path=last2.getProperty("FILEPATH").toString();
                        String Cancel=last2.getProperty("Cancel").toString();
                        String Status=last2.getProperty("Status").toString();
                        String Patient_Contact=last2.getProperty("PatientContact").toString();
                        String Patient_Email=last2.getProperty("PatientEmail").toString();
                        String AppoinmentId=last2.getProperty("AppointmentID").toString();
                        String Age=last2.getProperty("age").toString();
                        String Lab_Code=last2.getProperty("LabCode").toString();
                        String Row_Sent=last2.getProperty("RowSent").toString();
                        String QRCode=last2.getProperty("QRCode").toString();

                        array_previousAppoinments.add(0,new PreviousAppoinmentDataSource(Status1,Id,username_book,patient_id,Date,Time,
                                 Contact,Area,Address,Prefered_Lab,Remark,Flag,File_Name,File_Path,Cancel,Status,Patient_Contact,Patient_Email,
                                AppoinmentId,Age,Lab_Code,Row_Sent,QRCode));

                        recyclerView.setAdapter(bookAppoinmentAdapte);
                    }
                }
            }catch (Exception e){
                Log.e(TAG,e.getMessage());
            }
        }
    }
}
