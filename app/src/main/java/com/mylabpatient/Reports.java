package com.mylabpatient;

import android.Manifest;
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
import android.widget.TextView;

import com.mylabpatient.Adapter.DatewiseTestAdapter;
import com.mylabpatient.Adapter.FinalTestDetailsAdapter;
import com.mylabpatient.Adapter.TestReportDatetWiseAdapter;
import com.mylabpatient.Adapter.TestReportTestWiseAdapter;
import com.mylabpatient.Adapter.TestWiseDateAdapter;
import com.mylabpatient.ConstantClasses.CustomeToast;
import com.mylabpatient.ConstantClasses.NetworkCheck;
import com.mylabpatient.DataSource.CancelHomeVisit;
import com.mylabpatient.DataSource.DateWiseTestDataSource;
import com.mylabpatient.DataSource.FinalTestDetailsDataSource;
import com.mylabpatient.DataSource.ReportDateWiseDataSource;
import com.mylabpatient.DataSource.ReportTestWiseDataSource;
import com.mylabpatient.SharedPreference.PreferenceServices;
import com.mylabpatient.WebServices.Webservices;

import org.ksoap2.serialization.SoapObject;

import java.util.ArrayList;
import java.util.HashMap;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class Reports extends AppCompatActivity implements View.OnClickListener,
        TestReportTestWiseAdapter.OnItemClickListener,
        TestWiseDateAdapter.OnItemClickListenerTaseWiseDate,
        TestReportDatetWiseAdapter.onDateClick,
        DatewiseTestAdapter.OnTestClickListner
        {

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
    ArrayList<FinalTestDetailsDataSource> arrayList_FinalTestDetails=new ArrayList<>();
    ArrayList<DateWiseTestDataSource> arrayList_DateWiseTest=new ArrayList<>();
    TestReportTestWiseAdapter testReportTestWiseAdapter;
    TestReportDatetWiseAdapter testReportDatetWiseAdapter;
    Dialog TestwiseDateWisedialog,FinalTestDetailsDialog,DatewiseTestdialog;
    RecyclerView TestWiserecyclerView,DateWiserecyclerView;

    public static int TestDetailsPosition=-1,DateDetailsPosition=-1;

    public static String FinalTestName="";

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
        TestWiserecyclerView.setAdapter(testWiseDateAdapter);
        testWiseDateAdapter.setOnItemClickListener((TestWiseDateAdapter.OnItemClickListenerTaseWiseDate) Reports.this);
        ImageView imageView=(ImageView)TestwiseDateWisedialog.findViewById(R.id.TestWiseDateWiseClosedialog);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TestwiseDateWisedialog.dismiss();
            }
        });

        TestwiseDateWisedialog.show();
    }

    private void FinalTestDetailsDialogMethod() {
        FinalTestDetailsDialog=new Dialog(Reports.this);
        FinalTestDetailsDialog.setContentView(R.layout.finaltestlistview);
        FinalTestDetailsDialog.setCancelable(false);
        FinalTestDetailsDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        FinalTestDetailsDialog.setCanceledOnTouchOutside(false);
        TextView txtTitle=(TextView)FinalTestDetailsDialog.findViewById(R.id.FinaltestListTitle);
        if(!FinalTestName.toString().equals(""))
            txtTitle.setText(FinalTestName);

        RecyclerView FinalTestDetailsRecylearView=(RecyclerView)FinalTestDetailsDialog.findViewById(R.id.finalTestRecyclearView);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(Reports.this);
        FinalTestDetailsRecylearView.setLayoutManager(layoutManager);
        FinalTestDetailsAdapter finalTestDetailsAdapter=new FinalTestDetailsAdapter(Reports.this,arrayList_FinalTestDetails);
        FinalTestDetailsRecylearView.setAdapter(finalTestDetailsAdapter);
        ImageView imageView=(ImageView)FinalTestDetailsDialog.findViewById(R.id.testDetailsclosedialog);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FinalTestDetailsDialog.dismiss();
            }
        });

        FinalTestDetailsDialog.show();
    }
    private void DateWiseTest() {

         Log.e("Dialog","Enter");
         DatewiseTestdialog=new Dialog(Reports.this);
         DatewiseTestdialog.setContentView(R.layout.testlistdialog);
         DatewiseTestdialog.setCancelable(false);
         DatewiseTestdialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
         DatewiseTestdialog.setCanceledOnTouchOutside(false);

         DateWiserecyclerView=(RecyclerView)DatewiseTestdialog.findViewById(R.id.DateWiseTestWiseRecyclearView);
         RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(Reports.this);
         DateWiserecyclerView.setLayoutManager(layoutManager);
         DatewiseTestAdapter datewiseTestAdapter=new DatewiseTestAdapter(Reports.this,arrayList_DateWiseTest);
         DateWiserecyclerView.setAdapter(datewiseTestAdapter);
         datewiseTestAdapter.setOnTestClickListner((DatewiseTestAdapter.OnTestClickListner) Reports.this);
         ImageView imageView=(ImageView)DatewiseTestdialog.findViewById(R.id.datewisetestClosedialog);
         imageView.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 DatewiseTestdialog.dismiss();
             }
         });
         DatewiseTestdialog.show();
     }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btnReportSearch:
                SendingData();
                break;
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        Log.e("From Report",String.valueOf(position));

        TestDetailsPosition=position;
        ReportTestWiseDataSource reportTestWiseDataSource=arrayList_ReportTestwise.get(position);
        Log.e("TestId",reportTestWiseDataSource.getTestId());
        Log.e("TestName",reportTestWiseDataSource.getTestname());
        if(position == -1){
        }else{
            //after click on item dialog apear
            new TestWiseDate().execute(SendContact,SendContact,reportTestWiseDataSource.getTestId());
        }
    }

    @Override
    public void onItemClickTaseWiseDate(View view, int position) {
        Log.e("FromReport TestwiseDate",String.valueOf(position));

        ReportTestWiseDataSource reportTestWiseDataSource=arrayList_ReportTestwise.get(TestDetailsPosition);
        String TestId=reportTestWiseDataSource.getTestId();
        Log.e("TestId",reportTestWiseDataSource.getTestId());
        FinalTestName=reportTestWiseDataSource.getTestname();
        Log.e("TestName",reportTestWiseDataSource.getTestname());
        String Date=arrayList_TestWiseDate.get(position);
        if(position == -1){

        }else{
            new TestDetailsAccordingDateAndTestId().execute(SendContact,SendContact,TestId,Date);
        }
    }

     @Override
     public void onDateClick(View view, int position) {

       DateDetailsPosition=position;
       ReportDateWiseDataSource reportDateWiseDataSource=arrayList_ReportDateWise.get(position);
       String Date=reportDateWiseDataSource.getDate();
       if(position == -1){

        }else{
           new DateWiseTestAsyn().execute(SendContact,SendContact,Date);
       }
     }

     @Override
     public void onTestClick(View view, int position) {

         DateWiseTestDataSource dateWiseTestDataSource=arrayList_DateWiseTest.get(position);
         String testid=dateWiseTestDataSource.getTestId();
         ReportDateWiseDataSource reportDateWiseDataSource=arrayList_ReportDateWise.get(DateDetailsPosition);
         FinalTestName=dateWiseTestDataSource.getTestName();
         String Date=reportDateWiseDataSource.getDate();
         if(position == -1){

         }else{
             new TestDetailsAccordingDateAndTestId().execute(SendContact,SendContact,testid,Date);
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
                    testReportTestWiseAdapter.setOnItemClickListener((TestReportTestWiseAdapter.OnItemClickListener) Reports.this);

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
                    testReportDatetWiseAdapter.setonDateClick(Reports.this);
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

                    //clear it here
                    arrayList_TestWiseDate.clear();
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
    class TestDetailsAccordingDateAndTestId extends AsyncTask<String,Void,SoapObject> {

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
            SoapObject soapObject=webservices.Bind_PatientTestWiseDate(params[0],params[1],params[2],params[3]);
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
                    String CenterName;
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
                    //clear it
                    arrayList_FinalTestDetails.clear();
                    for(int i=0; i < last.getPropertyCount(); i++) {
                        //when data was increase last property was increase that y getting in the for loops
                        SoapObject last2= (SoapObject) last.getProperty(i);

                        String TitleName=last2.getProperty("TESTDET_FieldName").toString();
                        String FieldNo=last2.getProperty("TESTDET_FieldNo").toString();
                        String NormalValue=last2.getProperty("TESTDET_NormalValue").toString();
                        String MAxValue=last2.getProperty("TESTDET_MaxValue").toString();
                        String MinValue=last2.getProperty("TESTDET_MinValue").toString();
                        String DocName=last2.getProperty("DOCTOR_Name").toString();
                        try{
                            CenterName=last2.getProperty("CENTER_Name").toString();
                            if(CenterName.toString().trim().equalsIgnoreCase("anyType{}")){
                                CenterName="";
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                            CenterName="";
                        }
                        String FildValue=last2.getProperty("DOCDET_tFieldValue").toString();
                        String TestUnit=last2.getProperty("TESTDET_sUnit").toString();

                        arrayList_FinalTestDetails.add(new FinalTestDetailsDataSource(TitleName,FieldNo,NormalValue,MAxValue,MinValue,
                                DocName,CenterName,FildValue,TestUnit));
                    }
                    Log.e("Mhere","mhere");
                    if(count > 0){
                        if(TestwiseDateWisedialog != null && TestwiseDateWisedialog.isShowing()){
                            //dismiss the dialog
                            TestwiseDateWisedialog.dismiss();
                        }else if(DatewiseTestdialog != null && DatewiseTestdialog.isShowing()){
                            //if dailog open close it
                            DatewiseTestdialog.dismiss();
                        }
                        //show another dialog
                        FinalTestDetailsDialogMethod();
                    }else{
                        customeToast.CustomeToastSetting(Reports.this,"No Test Details");
                        Log.e("else","mhere");
                    }

                }
            }catch (Exception e){
                e.printStackTrace();
                //Log.e("Error",e.getMessage());
            }
        }
    }
    class DateWiseTestAsyn extends AsyncTask<String,Void,SoapObject> {

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
            SoapObject soapObject=webservices.Get_PatientTest_date(params[0],params[1],params[2]);
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
                    //clear it here
                    arrayList_DateWiseTest.clear();
                    for(int i=0; i < last.getPropertyCount(); i++) {
                        //when data was increase last property was increase that y getting in the for loops
                        SoapObject last2= (SoapObject) last.getProperty(i);
                        String testID=last2.getProperty("TESTMST_CurrentId").toString();
                        String testname=last2.getProperty("TESTMST_Name").toString();

                        arrayList_DateWiseTest.add(new DateWiseTestDataSource(testID,testname));
                    }
                    if(count > 0){
                        //show that dialog
                        DateWiseTest();
                    }else{
                        customeToast.CustomeToastSetting(Reports.this,"No Dates");
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
                //Log.e("Error",e.getMessage());
            }
        }
    }
}
