package com.mylabpatient;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mylabpatient.Adapter.UpcommingAdapter;
import com.mylabpatient.ConstantClasses.CustomeToast;
import com.mylabpatient.ConstantClasses.NetworkCheck;
import com.mylabpatient.DataSource.CancelAppoinmentData;
import com.mylabpatient.DataSource.UpcommingEventsList;
import com.mylabpatient.SharedPreference.PreferenceServices;
import com.mylabpatient.WebServices.Webservices;

import org.ksoap2.serialization.SoapObject;

import java.util.ArrayList;
import java.util.HashMap;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class Home extends AppCompatActivity implements View.OnClickListener {

    TextView txtProfileName;
    ImageView ProfileImage;
    RelativeLayout layHomeVisit,layAppoinment,layReport,layProfile,layPassword;
    Dialog dialog;
    CustomeToast customeToast=new CustomeToast();
    public static HashMap<String,String> map=new HashMap<>();
    RecyclerView recyclerView;
    SweetAlertDialog progressDialog;
    static final String TAG="HomeActivity";
    ArrayList<UpcommingEventsList> arrayList_UpcommingData=new ArrayList<>();
    UpcommingAdapter upcommingAdapter;
    NetworkCheck networkCheck;

    String SendContact,SendPassword,SendNewPassword,CheckOldPasswordFlag="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        try{
            getSupportActionBar().setTitle("Home");
        }catch (Exception e){
            Log.e(getLocalClassName(),e.getMessage());
        }

        //initialosing the map for getting profileName and gender
        map=PreferenceServices.getInstance(Home.this).getLoginDetails();

        //initializing the appoinemtn
        progressDialog = new SweetAlertDialog(Home.this, SweetAlertDialog.PROGRESS_TYPE);
        progressDialog.getProgressHelper().setBarColor(Color.parseColor("#EF6C00"));
        progressDialog.setTitleText("Loading");
        progressDialog.setCancelable(false);

        //cheking the login status
        if(PreferenceServices.getInstance(Home.this).getUserLoginStatus().equalsIgnoreCase("true")){
            //if login stay here
            init();
            //setting the profile Name and Gender
            settingData();
            //intializing the dialog
            //dialog=new Dialog(Home.this);

            //initiazling the network constant class
            networkCheck=new NetworkCheck(Home.this);
            if(networkCheck.isConnectinToInternet()){
                new UpcommingHomeVisitsAsync().execute();
            }else{
                customeToast.CustomeToastSetting(Home.this,"Internet Connection Required");
            }

            //initiaizing the adapter class
            upcommingAdapter=new UpcommingAdapter(Home.this,arrayList_UpcommingData);
            RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(Home.this);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(upcommingAdapter);
        }else if(PreferenceServices.getInstance(Home.this).getUserLoginStatus().equalsIgnoreCase("false")){
            //if not go to login Page
            startActivity(new Intent(Home.this,Login.class));
            finish();
        }
    }

    private void init(){

        layHomeVisit=(RelativeLayout)findViewById(R.id.MainHomeVisit);
        layAppoinment=(RelativeLayout)findViewById(R.id.MainBookAppoinment);
        layReport=(RelativeLayout)findViewById(R.id.MainReports);
        layProfile=(RelativeLayout)findViewById(R.id.MainProfile);
        layPassword=(RelativeLayout)findViewById(R.id.MainPassword);

        txtProfileName=(TextView)findViewById(R.id.txtProfilePatientName);
        ProfileImage=(ImageView)findViewById(R.id.homeProfileImage);

        recyclerView=(RecyclerView)findViewById(R.id.MainUpCommingList);

        layHomeVisit.setOnClickListener(this);
        layAppoinment.setOnClickListener(this);
        layReport.setOnClickListener(this);
        layProfile.setOnClickListener(this);
        layPassword.setOnClickListener(this);

        SendContact=map.get(PreferenceServices.Contact);
    }
    private void settingData(){
        txtProfileName.setText(map.get(PreferenceServices.PatientName));

        if(map.get(PreferenceServices.Gender).trim().equals("Male")){
            //ProfileImage.setBackgroundColor(Color.parseColor("#29B6F6"));
            ProfileImage.setBackgroundResource(R.drawable.imagebackgroundround);
        }else{
            //ProfileImage.setBackgroundColor(Color.parseColor("#F48FB1"));
            ProfileImage.setBackgroundResource(R.drawable.imagebackgroundround2);
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {

        int id=v.getId();
        switch (id){
            case R.id.MainHomeVisit:
                startActivity(new Intent(Home.this,HomeVisits.class));
                break;
            case R.id.MainBookAppoinment:
                startActivity(new Intent(Home.this,Appoinment.class));
                break;
            case R.id.MainReports:
                startActivity(new Intent(Home.this,Reports.class));
                break;
            case R.id.MainProfile:
                startActivity(new Intent(Home.this,Profile.class));
                break;
            case R.id.MainPassword:
                AddingDialog();
                break;
        }

    }

    private void AddingDialog(){
        dialog=new Dialog(Home.this);
        dialog.setContentView(R.layout.changepasswordialog);
        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);
        ImageView imageView=(ImageView)dialog.findViewById(R.id.closedialog);
        final EditText edtoldpass=(EditText)dialog.findViewById(R.id.edtPasswordOldPassword);
        final EditText edtnewPass=(EditText)dialog.findViewById(R.id.edtPasswordNewPassword);
        final EditText edtnewpassconfirm=(EditText)dialog.findViewById(R.id.edtPasswordNewPasswordConfirm);
        Button btncancel=(Button)dialog.findViewById(R.id.btnPasswordCancel);
        Button btnSubmit=(Button)dialog.findViewById(R.id.btnPasswordSave);

        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtoldpass.getText().toString().length() == 0 && edtoldpass.getText().toString().equals("")){
                    edtoldpass.setError("Enter Old Password");
                }else if(edtnewPass.getText().toString().length() == 0 && edtnewPass.getText().toString().equals("")){
                    edtnewPass.setError("Enter New PassWord");
                }else if(edtnewpassconfirm.getText().toString().length() == 0 && edtnewpassconfirm.getText().toString().equals("")){
                    edtnewpassconfirm.setError("Please Confirm Your Password");
                }else if(!edtnewPass.getText().toString().trim().equals(edtnewpassconfirm.getText().toString().trim())){
                    customeToast.CustomeToastSetting(Home.this,"Confirm password not match");
                }else{
                    SendPassword=edtoldpass.getText().toString();
                    SendNewPassword=edtnewPass.getText().toString();
                    new CheckOldPassword().execute(SendContact,SendPassword);
                }
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        //show dialog
        dialog.show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id=item.getItemId();

        switch (id){
            case R.id.logout:
                PreferenceServices.getInstance(Home.this).saveUserLoginStatus("false");
                this.finish();
                startActivity(new Intent(Home.this,Home.class));
                customeToast.CustomeToastSetting(Home.this,"Logout Succesfully");
                break;
        }
        return true;
    }

    class UpcommingHomeVisitsAsync extends AsyncTask<Void,Void,SoapObject>{

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
            SoapObject soapObject=webservices.UpCommingEvents("1");
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

                        String FromDate=last2.getProperty("FROM_x0020_DATE").toString();
                        String ToDate=last2.getProperty("TO_x0020_DATE").toString();
                        String Package=last2.getProperty("PACKKAGE_x0020_NAME").toString();
                        String TestName=last2.getProperty("TEST_x0020_NAME").toString();
                        String Price=last2.getProperty("PRICE").toString();

                        arrayList_UpcommingData.add(0,new UpcommingEventsList(FromDate,ToDate,Package,TestName,Price));
                        recyclerView.setAdapter(upcommingAdapter);
                    }
                }
            }catch (Exception e){
                Log.e(TAG,e.getMessage());
            }
        }
    }
    class CheckOldPassword extends AsyncTask<String,Void,SoapObject>{
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
            SoapObject soapObject=webservices.patient_check_old_password(params[0],params[1]);
            return soapObject;
        }
        @Override
        protected void onPostExecute(SoapObject soapObject) {
            super.onPostExecute(soapObject);
            try{
                if(progressDialog != null && progressDialog.isShowing())
                    progressDialog.dismiss();

                if(soapObject == null){
                    Log.e(getLocalClassName(),soapObject.toString());
                }else{
                    Log.e(getLocalClassName(),soapObject.toString());

                    CheckOldPasswordFlag=soapObject.getProperty("patient_check_old_passwordResult").toString();
                    Log.e("CheckOldPasswordFlag",CheckOldPasswordFlag);

                    //flag 1 means patient alredy exists
                    if(CheckOldPasswordFlag.equals("1")){
                        //if old password match then
                        //call the change password method
                        new new_Password_patient().execute(SendContact,SendNewPassword);
                        Log.e("CheckOldPasswordFlag",CheckOldPasswordFlag);
                    }else{
                        customeToast.CustomeToastSetting(Home.this,"Old Password Not Match Please Insert Properly ");
                        Log.e("CheckOldPasswordFlag",CheckOldPasswordFlag);
                    }
                }
            }catch (Exception e){
                Log.e(getLocalClassName(),e.getMessage());
            }
        }
    }
    class new_Password_patient extends AsyncTask<String,Void,SoapObject>{
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
            SoapObject soapObject=webservices.new_Password_patient(params[0],params[1]);
            return soapObject;
        }
        @Override
        protected void onPostExecute(SoapObject soapObject) {
            super.onPostExecute(soapObject);
            try{
                if(progressDialog != null && progressDialog.isShowing())
                    progressDialog.dismiss();

                //dismiss the dialog
                dialog.dismiss();
                //print massege
                customeToast.CustomeToastSetting(Home.this,"Password Change Succesfully");

                if(soapObject == null){
                    Log.e(getLocalClassName(),soapObject.toString());
                }else{
                    Log.e(getLocalClassName(),soapObject.toString());
                }
            }catch (Exception e){
                Log.e(getLocalClassName(),e.getMessage());
            }
        }
    }
}
