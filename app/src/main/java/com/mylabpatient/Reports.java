package com.mylabpatient;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.mylabpatient.Adapter.TestReportDatetWiseAdapter;
import com.mylabpatient.Adapter.TestReportTestWiseAdapter;
import com.mylabpatient.Adapter.TestWiseDateAdapter;
import com.mylabpatient.ConstantClasses.CustomeToast;
import com.mylabpatient.ConstantClasses.NetworkCheck;
import com.mylabpatient.DataSource.CancelHomeVisit;
import com.mylabpatient.DataSource.ReportDateWiseDataSource;
import com.mylabpatient.DataSource.ReportTestWiseDataSource;
import com.mylabpatient.SharedPreference.PreferenceServices;
import com.mylabpatient.WebServices.Webservices;

import org.ksoap2.serialization.SoapObject;

import java.util.ArrayList;
import java.util.HashMap;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class Reports extends AppCompatActivity implements View.OnClickListener{

    RadioGroup rdgReportSelection;
    RadioButton rdbTestWise,rdbDateWise;
    Button btnSearch;
    NetworkCheck networkCheck;
    SweetAlertDialog progressDialog;
    RecyclerView recyclerView;
    CustomeToast customeToast=new CustomeToast();
    public static HashMap<String,String> map=new HashMap<>();
    String SendContact,SendEmail;
    ArrayList<ReportTestWiseDataSource> arrayList_ReportTestwise=new ArrayList<>();
    ArrayList<ReportDateWiseDataSource> arrayList_ReportDateWise=new ArrayList<>();
    ArrayList<String> arrayList_TestWiseDate=new ArrayList<>();
    TestReportTestWiseAdapter testReportTestWiseAdapter;
    TestReportDatetWiseAdapter testReportDatetWiseAdapter;
    Dialog TestwiseDateWisedialog;
    RecyclerView TestWiserecyclerView;

    //String for SEnding Testwise
    String SendContactTest2,SendEmailTest2,SendTestId2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Login);
        setContentView(R.layout.activity_reports);

        //initializing the widget here
        init();

        //getting details from shared preferences
        map=PreferenceServices.getInstance(Reports.this).getLoginDetails();
        SendContact=map.get(PreferenceServices.Contact);
        SendEmail=map.get(PreferenceServices.Email);

        //initializing the progress dialog
        progressDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        progressDialog.getProgressHelper().setBarColor(Color.parseColor("#EF6C00"));
        progressDialog.setTitleText("Please Wait..");
        progressDialog.setCancelable(false);

        //initializing the network constatn class
        networkCheck=new NetworkCheck(this);
    }
    private void init() {

        rdgReportSelection=(RadioGroup)findViewById(R.id.RdgreportTestSelection);
        rdbDateWise=(RadioButton)findViewById(R.id.rdbReportDatewise);
        rdbTestWise=(RadioButton)findViewById(R.id.rdbReportTestwise);
        btnSearch=(Button)findViewById(R.id.btnReportSearch);
        recyclerView=(RecyclerView)findViewById(R.id.testrecyclearview);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(Reports.this);
        recyclerView.setLayoutManager(layoutManager);

        btnSearch.setOnClickListener(this);

    }
    private void SendingData() {

        if (!rdbDateWise.isChecked() && !rdbTestWise.isChecked()) {
            customeToast.CustomeToastSetting(Reports.this, "Select Wich How Classified Test");
        } else {
            if (rdbTestWise.isChecked()) {
                new TestWiseListAsyn().execute();
            } else if (rdbDateWise.isChecked()) {
                new DateWiseListAsyn().execute();
            } else {
            }
        }
    }
    private void TestwiseDateWiseDialogMethod() {

        TestwiseDateWisedialog=new Dialog(Reports.this);
        TestwiseDateWisedialog.setContentView(R.layout.testwisedialog);
        TestwiseDateWisedialog.setCancelable(false);
        TestwiseDateWisedialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        TestwiseDateWisedialog.setCanceledOnTouchOutside(false);
        TestWiserecyclerView=(RecyclerView)TestwiseDateWisedialog.findViewById(R.id.TestWiseDateWiseRecyclearView);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(Reports.this);
        TestWiserecyclerView.setLayoutManager(layoutManager);
        TestWiseDateAdapter testWiseDateAdapter=new TestWiseDateAdapter(Reports.this,arrayList_TestWiseDate);
        recyclerView.setAdapter(testWiseDateAdapter);

        ImageView imageView=(ImageView)TestwiseDateWisedialog.findViewById(R.id.TestWiseDateWiseClosedialog);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TestwiseDateWisedialog.dismiss();
            }
        });


        TestwiseDateWisedialog.show();
    }
    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btnReportSearch:
                SendingData();
                break;
        }
    }
    class TestWiseListAsyn extends AsyncTask<Void,Void,SoapObject> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            try{
                if(progressDialog != null && !progressDialog.isShowing())
                    progressDialog.show();

            }catch (Exception e){
                Log.e(getLocalClassName(),e.getMessage());
            }
        }

        @Override
        protected SoapObject doInBackground(Void... params) {
            Webservices webservices=new Webservices();
            SoapObject soapObject=webservices.Get_PatientTest(SendContact,SendEmail);
            return soapObject;
        }

        @Override
        protected void onPostExecute(SoapObject soapObject) {
            super.onPostExecute(soapObject);
            try{
                if(progressDialog != null && progressDialog.isShowing())
                    progressDialog.dismiss();
            }catch (Exception e){
                Log.e(getLocalClassName(),e.getMessage());
            }
            try{
                if(soapObject == null){
                    Log.e("No Data",soapObject.toString());
                }else{
                    Log.e(getLocalClassName(),soapObject.toString());

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
                    Log.e("Test wise Count : ",String.valueOf(count));

                    arrayList_ReportTestwise.clear();
                    for(int i=0; i < last.getPropertyCount(); i++) {
                        //when data was increase last property was increase that y getting in the for loops
                        SoapObject last2= (SoapObject) last.getProperty(i);

                        //String Status1=last2.getProperty("Status1").toString();
                        String testId=last2.getProperty("TESTMST_CurrentId").toString();
                        String testName=last2.getProperty("TESTMST_Name").toString();

                        arrayList_ReportTestwise.add(new ReportTestWiseDataSource(testId,testName));
                        Log.e("testId",testId);
                    }
                    Log.e("Size",String.valueOf(arrayList_ReportTestwise.size()));
                    testReportTestWiseAdapter=new TestReportTestWiseAdapter(Reports.this,arrayList_ReportTestwise);
                    recyclerView.setAdapter(testReportTestWiseAdapter);

//                    int position=-1;
//                    position=TestReportTestWiseAdapter.getSettingData();
//                    Log.e("Position",String.valueOf(position));
//
//                    ReportTestWiseDataSource reportTestWiseDataSource=arrayList_ReportTestwise.get(position);
//                    Log.e("TestId",reportTestWiseDataSource.getTestId());
//                    Log.e("TestName",reportTestWiseDataSource.getTestname());
//
//                    if(position == -1){
//
//                    }else{
//                        //after click on item dialog apear
//                        new TestWiseDate().execute(SendContact,SendContact,reportTestWiseDataSource.getTestId());
//                    }

                }
            }catch (Exception e){
                e.printStackTrace();
                Log.e("Error",e.getMessage());
            }
        }
    }
    class DateWiseListAsyn extends AsyncTask<Void,Void,SoapObject> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            try{
                if(progressDialog != null && !progressDialog.isShowing())
                    progressDialog.show();

            }catch (Exception e){
                Log.e(getLocalClassName(),e.getMessage());
            }
        }

        @Override
        protected SoapObject doInBackground(Void... params) {
            Webservices webservices=new Webservices();
            SoapObject soapObject=webservices.Get_PatientDate(SendContact,SendEmail);
            return soapObject;
        }

        @Override
        protected void onPostExecute(SoapObject soapObject) {
            super.onPostExecute(soapObject);
            try{
                if(progressDialog != null && progressDialog.isShowing())
                    progressDialog.dismiss();
            }catch (Exception e){
                Log.e(getLocalClassName(),e.getMessage());
            }
            try{
                if(soapObject == null){
                    Log.e("No Data",soapObject.toString());
                }else{
                    Log.e(getLocalClassName(),soapObject.toString());

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
                    Log.e("Date wise Count : ",String.valueOf(count));

                    arrayList_ReportDateWise.clear();
                    for(int i=0; i < last.getPropertyCount(); i++) {
                        //when data was increase last property was increase that y getting in the for loops
                        SoapObject last2= (SoapObject) last.getProperty(i);

                        String testDate=last2.getProperty("REPORTDATE").toString();
                        Log.e("date",testDate);
                        arrayList_ReportDateWise.add(new ReportDateWiseDataSource(testDate));
                    }
                    Log.e("Size",String.valueOf(arrayList_ReportDateWise.size()));
                    testReportDatetWiseAdapter=new TestReportDatetWiseAdapter(Reports.this,arrayList_ReportDateWise);
                    recyclerView.setAdapter(testReportDatetWiseAdapter);
                }
            }catch (Exception e){
                e.printStackTrace();
                Log.e("Error",e.getMessage());
            }
        }
    }
    class TestWiseDate extends AsyncTask<String,Void,SoapObject> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            try{
                if(progressDialog != null && !progressDialog.isShowing())
                    progressDialog.show();

            }catch (Exception e){
                Log.e(getLocalClassName(),e.getMessage());
            }
        }

        @Override
        protected SoapObject doInBackground(String... params) {
            Webservices webservices=new Webservices();
            SoapObject soapObject=webservices.Bind_PatientTestWise(params[0],params[1],params[2]);
            return soapObject;
        }

        @Override
        protected void onPostExecute(SoapObject soapObject) {
            super.onPostExecute(soapObject);
            try{
                if(progressDialog != null && progressDialog.isShowing())
                    progressDialog.dismiss();
            }catch (Exception e){
                Log.e(getLocalClassName(),e.getMessage());
            }
            try{
                if(soapObject == null){
                    Log.e("No Data",soapObject.toString());
                }else{
                    Log.e(getLocalClassName(),soapObject.toString());

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
                    Log.e("Test Wise Date Count : ",String.valueOf(count));

                    for(int i=0; i < last.getPropertyCount(); i++) {
                        //when data was increase last property was increase that y getting in the for loops
                        SoapObject last2= (SoapObject) last.getProperty(i);
                        String testDate=last2.getProperty("REPORTDATE").toString();
                        arrayList_TestWiseDate.add(testDate);
                    }
                    if(count > 0){
                        //after Selection it call
                        TestwiseDateWiseDialogMethod();
                    }else{
                        customeToast.CustomeToastSetting(Reports.this,"No Dates");
                    }

                }
            }catch (Exception e){
                e.printStackTrace();
                Log.e("Error",e.getMessage());
            }
        }
    }

}
