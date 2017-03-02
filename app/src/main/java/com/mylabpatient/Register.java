package com.mylabpatient;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.mylabpatient.ConstantClasses.CustomeToast;
import com.mylabpatient.ConstantClasses.NetworkCheck;
import com.mylabpatient.ConstantClasses.emailValidator;
import com.mylabpatient.WebServices.Webservices;

import org.ksoap2.serialization.SoapObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class Register extends AppCompatActivity implements View.OnClickListener {

    EditText edtName,edtContact,edtEmail,edtDob,edtArea,edtPincode,edtAdd,edtRegisterAge;
    Spinner spiCity,spiState;
    RadioGroup rdg_gender;
    RadioButton rdbMale,rdbFemle;
    Button btnBackLogin,btnRegister;
    emailValidator emailValidator=new emailValidator();
    SweetAlertDialog pDialog;
    ArrayList<String> stateArrayList=new ArrayList<>();
    ArrayList<String> cityArrayList=new ArrayList<>();
    NetworkCheck networkCheck;
    CustomeToast customeToast=new CustomeToast();
    Dialog dialog,UpdatePasswordDialog;
    SweetAlertDialog progressDialog;

    String SendName,SendContact,SendEmail,SendDob,SendAge,SendGender,
            SendCity,SendArea,SendPincode,SendState,SendAddress;

    //flag 1 if alredy exists
    //flag 0 if new
    String PatientAlreadyExistsFlag="",OTPFlag="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setting the new theme in which no action bar
        setTheme(R.style.Login);
        setContentView(R.layout.activity_register);

        //initializing the progress dialog
        pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#EF6C00"));
        pDialog.setTitleText("Loading");
        pDialog.setCancelable(false);

        //initializing the widgets
        init();

        //initializing the custome dialog
        dialog=new Dialog(Register.this);
        UpdatePasswordDialog=new Dialog(Register.this);

        networkCheck=new NetworkCheck(this);
        if(networkCheck.isConnectinToInternet()){
            new AllstateSync().execute();
        }else{
            customeToast.CustomeToastSetting(Register.this,"Inter Connection Requaired");
        }
    }
    private void init(){
        edtName=(EditText)findViewById(R.id.edtRegisterName);
        edtContact=(EditText)findViewById(R.id.edtRegisterContact);
        edtEmail=(EditText)findViewById(R.id.edtRegisterEmail);
        edtDob=(EditText)findViewById(R.id.edtRegisterDob);
        edtArea=(EditText)findViewById(R.id.edtRegisterArea);
        edtPincode=(EditText)findViewById(R.id.edtRegisterPincode);
        edtAdd=(EditText)findViewById(R.id.edtRegisterAdd);
        spiState=(Spinner)findViewById(R.id.spiRegisterState);
        spiCity=(Spinner)findViewById(R.id.spiRegisterCity);
        rdg_gender=(RadioGroup)findViewById(R.id.RegisterGenderRdg);
        rdbMale=(RadioButton)findViewById(R.id.rdbRegisterRbdMale);
        rdbFemle=(RadioButton)findViewById(R.id.rdbRegisterRbdFemail);
        btnBackLogin=(Button)findViewById(R.id.btnRegisterLogin);
        btnRegister=(Button)findViewById(R.id.btnRegisterRegister);
        edtRegisterAge=(EditText)findViewById(R.id.edtRegisterAge);

        btnBackLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);

        //making edittest focasable false
        edtDob.setFocusable(false);
        edtDob.setFocusableInTouchMode(false);
        edtDob.setClickable(true);

        //code for taking the dob from user
        final Calendar calendar=Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener date=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                calendar.set(Calendar.YEAR,year);
                calendar.set(Calendar.MONTH,month);
                calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);

                SimpleDateFormat dateFormat= new SimpleDateFormat("dd/MM/yyyy");
                edtDob.setText(dateFormat.format(calendar.getTime()));
            }
        };


        edtDob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new DatePickerDialog(Register.this,date,calendar.get(Calendar.YEAR),
                        calendar.get(calendar.MONTH),calendar.get(calendar.DAY_OF_MONTH)).show();
            }
        });

        spiState.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position != 0){
                    if(networkCheck.isConnectinToInternet()){
                        new getCityAccordingState().execute(String.valueOf(spiState.getSelectedItem()));
                    }else{
                        customeToast.CustomeToastSetting(Register.this,"Internet Connnection Requared");
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void sendingData(){
        if(edtName.getText().toString().equals("") && edtName.getText().toString().length() == 0){
            edtName.setError("Enter Name");
        }else if(edtContact.getText().toString().equals("") && !(edtContact.toString().length() < 10)){
            edtContact.setError("Enter Contact Properly");
        }else if(edtEmail.getText().toString().equals("") && edtEmail.getText().toString().length() == 0){
            edtEmail.setError("Enter Email");
        }else if(!emailValidator.emailValidatorcheck(edtEmail.getText().toString())){
            edtEmail.setError("Enter Proper Email Validation Not Match");
            customeToast.CustomeToastSetting(Register.this,"Enter Proper Email Validation Not Match");
        }else if(edtDob.getText().toString().equals("") && edtDob.getText().toString().length() == 0){
            edtDob.setError("Enter Date Of Birth");
        }else if(edtRegisterAge.getText().toString().length() == 0 && edtRegisterAge.getText().toString().equals("")){
            edtRegisterAge.setError("Enter Age");
        }else if(edtArea.getText().toString().equals("") && edtArea.getText().toString().length() == 0){
            edtArea.setError("Enter Area");
        }else if(edtPincode.getText().toString().equals("") && edtPincode.getText().toString().length() == 0){
            edtPincode.setError("Enter Pincode");
        }else if(edtAdd.getText().toString().equals("") && edtAdd.getText().toString().length() == 0){
            edtAdd.setError("Enter Address");
        }else if(!rdbMale.isChecked() && !rdbFemle.isChecked()){
            customeToast.CustomeToastSetting(Register.this,"Select Gender ");
        }else{
            SendName=edtName.getText().toString();
            SendContact=edtContact.getText().toString();
            SendEmail=edtEmail.getText().toString();
            SendDob=edtDob.getText().toString();
            SendAge=edtRegisterAge.getText().toString();
            if(rdbMale.isChecked()){
                SendGender="Male";
            }else{
                SendGender="Female";
            }
            SendState=spiState.getSelectedItem().toString();
            SendCity=spiCity.getSelectedItem().toString();
            SendArea=edtArea.getText().toString();
            SendPincode=edtPincode.getText().toString();
            SendAddress=edtAdd.getText().toString();

            if(networkCheck.isConnectinToInternet()){
                //cheking customer alredy exists
                new CheckPatientAlreadyExists().execute();
            }else{
                customeToast.CustomeToastSetting(Register.this,"Internet Connection Required ");
            }


        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btnRegisterLogin:
                startActivity(new Intent(Register.this,Login.class));
                this.finish();
                break;
            case R.id.btnRegisterRegister:
                sendingData();
                break;
        }

    }
    private void OTPDialog() {

        //dialog=new Dialog(Register.this);
        dialog.setContentView(R.layout.otpdialoglayout);
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCanceledOnTouchOutside(false);
        ImageView imageView=(ImageView)dialog.findViewById(R.id.otpclosedialog);
        Button btnSubmit=(Button)dialog.findViewById(R.id.btnOtpSubmit);
        final EditText edtOtp=(EditText)dialog.findViewById(R.id.edtOTPForRegistration);
       // Button btnCancel=(Button)dialog.findViewById(R.id.btnOtpCancel);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(edtOtp.getText().toString().length() == 0 &&
                        edtOtp.getText().toString().equals("")){
                    edtOtp.setError("Please Enter OTP");
                }else{
                    new CheckOtpAsyn().execute(SendContact,edtOtp.getText().toString());
                }
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
    private void UpdatePasswordDialog() {

       // UpdatePasswordDialog=new Dialog(Register.this);
        UpdatePasswordDialog.setContentView(R.layout.newpasswordtaking_registration);
        UpdatePasswordDialog.setCancelable(false);
        UpdatePasswordDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        UpdatePasswordDialog.setCanceledOnTouchOutside(false);
        ImageView imageView=(ImageView)UpdatePasswordDialog.findViewById(R.id.passwordclosedialog);
        Button btnSubmit=(Button)UpdatePasswordDialog.findViewById(R.id.btnupdatePasssubmit);
        final EditText edtpassword=(EditText)UpdatePasswordDialog.findViewById(R.id.edtPasswordREgistration);
        final EditText edtconfirmPass=(EditText)UpdatePasswordDialog.findViewById(R.id.edtConfirmPasswordREgistration);
        // Button btnCancel=(Button)dialog.findViewById(R.id.btnOtpCancel);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(edtpassword.getText().toString().length() == 0 &&
                        edtpassword.getText().toString().equals("")){
                    edtpassword.setError("Enter Password");
                }else if(edtconfirmPass.getText().toString().length() == 0 &&
                        edtconfirmPass.getText().toString().equals("")){
                    edtconfirmPass.setError("Please Confirm Pass");
                }else if(!edtpassword.getText().toString().trim().equals(edtconfirmPass.getText().toString().trim())){
                    customeToast.CustomeToastSetting(Register.this,"Password Not Match");
                }else{
                    new UpdatePasswordAsyn().execute(SendContact,edtconfirmPass.getText().toString());
                }

            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdatePasswordDialog.dismiss();
            }
        });

        UpdatePasswordDialog.show();
    }

    class AllstateSync extends AsyncTask<Void,Void,SoapObject>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            try{
                if(pDialog != null && !pDialog.isShowing())
                    pDialog.show();

            }catch (Exception e){
                Log.e(getLocalClassName(),e.getMessage());
            }
        }

        @Override
        protected SoapObject doInBackground(Void... params) {

            Webservices webservices=new Webservices();
            SoapObject soapObject=webservices.GETAllSTATE();
            return soapObject;
        }

        @Override
        protected void onPostExecute(SoapObject soapObject) {
            super.onPostExecute(soapObject);
            try{
                if(pDialog != null && pDialog.isShowing())
                    pDialog.dismiss();

            }catch (Exception e){
                Log.e(getLocalClassName(),e.getMessage());
            }

            try{
                if (soapObject == null) {
                    Log.e("State Value null : ",soapObject.toString());
                }else{

                    stateArrayList.clear();
                    stateArrayList.add("Select State");
                    Log.e("State Data : ",soapObject.toString());

                    SoapObject soap = (SoapObject) soapObject.getProperty(0);
                    System.out.println("soap : : "+ soap.getPropertyCount() +"  "+ soap.toString() );

                    SoapObject res = (SoapObject) soap.getProperty(0);
                    System.out.println("res : : "+ res.getPropertyCount() +"  "+ res.toString() );

                    SoapObject res2 = (SoapObject) soap.getProperty(1);
                    System.out.println("res2 : : "+ res2.getPropertyCount() +"  "+ res2.toString() );

                    SoapObject last = (SoapObject) res2.getProperty(0);
                    System.out.println("last : : "+ last.getPropertyCount() +"  "+ last.toString() );

                    int count=last.getPropertyCount();
                    Log.e("Count : ",String.valueOf(count));

                    for(int i=0; i < last.getPropertyCount()/*2*/; i++) {
                        //when data was increase last property was increase that y getting in the for loops
                        SoapObject last2= (SoapObject) last.getProperty(i);
                        String state=last2.getProperty("State").toString();
                        stateArrayList.add(state);
                    }

                    ArrayAdapter stataAdapter=new ArrayAdapter(Register.this,android.R.layout.simple_list_item_1,stateArrayList);
                    spiState.setAdapter(stataAdapter);
                }
            }catch (Exception e){
                Log.e(getLocalClassName(),e.getMessage());
            }
        }
    }
    class getCityAccordingState extends AsyncTask<String,Void,SoapObject>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            try{
                if(pDialog != null && !pDialog.isShowing())
                    pDialog.show();

            }catch (Exception e){
                Log.e(getLocalClassName(),e.getMessage());
            }
        }

        @Override
        protected SoapObject doInBackground(String... params) {

            Webservices webservices=new Webservices();
            SoapObject soapObject=webservices.GETAllCITYACCORDINGASTATE(params[0]);
            return soapObject;
        }

        @Override
        protected void onPostExecute(SoapObject soapObject) {
            super.onPostExecute(soapObject);
            try{
                if(pDialog != null && pDialog.isShowing())
                pDialog.dismiss();

            }catch (Exception e){
                Log.e(getLocalClassName(),e.getMessage());
            }
            try{
                if(soapObject == null){
                    Log.e(getLocalClassName(),soapObject.toString());
                }else{

                    cityArrayList.clear();
                    cityArrayList.add("Select City");
                    Log.e("State Data : ",soapObject.toString());

                    SoapObject soap = (SoapObject) soapObject.getProperty(0);
                    System.out.println("soap : : "+ soap.getPropertyCount() +"  "+ soap.toString() );

                    SoapObject res = (SoapObject) soap.getProperty(0);
                    System.out.println("res : : "+ res.getPropertyCount() +"  "+ res.toString() );

                    SoapObject res2 = (SoapObject) soap.getProperty(1);
                    System.out.println("res2 : : "+ res2.getPropertyCount() +"  "+ res2.toString() );

                    SoapObject last = (SoapObject) res2.getProperty(0);
                    System.out.println("last : : "+ last.getPropertyCount() +"  "+ last.toString() );

                    int count=last.getPropertyCount();
                    Log.e("Count : ",String.valueOf(count));

                    for(int i=0; i < last.getPropertyCount()/*2*/; i++) {
                        //when data was increase last property was increase that y getting in the for loops
                        SoapObject last2= (SoapObject) last.getProperty(i);
                        String state=last2.getProperty("City").toString();
                        cityArrayList.add(state);
                    }
                    ArrayAdapter cityAdapter=new ArrayAdapter(Register.this,android.R.layout.simple_list_item_1,cityArrayList);
                    spiCity.setAdapter(cityAdapter);
                }
            }catch (Exception e){
                Log.e(getLocalClassName(),e.getMessage());
            }
        }
    }
    class CheckPatientAlreadyExists extends AsyncTask<Void,Void,SoapObject>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            try{
                if(pDialog != null && !pDialog.isShowing())
                    pDialog.show();

            }catch (Exception e){
                Log.e(getLocalClassName(),e.getMessage());
            }
        }

        @Override
        protected SoapObject doInBackground(Void... params) {
            Webservices webservices=new Webservices();
            SoapObject soapObject=webservices.CHECK_PATIENTEXITS(SendContact,SendEmail);
            return soapObject;
        }

        @Override
        protected void onPostExecute(SoapObject soapObject) {
            super.onPostExecute(soapObject);
            try{
                if(pDialog != null && pDialog.isShowing())
                    pDialog.dismiss();

                if(soapObject == null){
                    Log.e(getLocalClassName(),soapObject.toString());
                }else{
                    Log.e(getLocalClassName(),soapObject.toString());

                    PatientAlreadyExistsFlag=soapObject.getProperty("CHKExistPatientResult").toString();
                    Log.e("PatientFlag",PatientAlreadyExistsFlag);

                    //flag 1 means patient alredy exists
                    if(PatientAlreadyExistsFlag.equals("1")){
                        customeToast.CustomeToastSetting(Register.this,"Patient Already Exists");
                        Log.e("PatientExistsFlag",PatientAlreadyExistsFlag);
                    }else{
                        new InserPatientInfoAsyn().execute();
                        Log.e("PatientExistsFlag",PatientAlreadyExistsFlag);
                        //sending data in password webservices
                        new PasswordAsync().execute();
                        //after succesfull login wait for otp
                        OTPDialog();
                    }
                }
            }catch (Exception e){
                Log.e(getLocalClassName(),e.getMessage());
            }
        }
    }
    class InserPatientInfoAsyn extends AsyncTask<Void,Void,SoapObject>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            try{
                if(pDialog != null && !pDialog.isShowing())
                    pDialog.show();

            }catch (Exception e){
                Log.e(getLocalClassName(),e.getMessage());
            }
        }

        @Override
        protected SoapObject doInBackground(Void... params) {

            Webservices webservices=new Webservices();
            SoapObject soapObject=webservices.INSERT_PATIENT_PORTAL(SendName,SendContact,SendEmail,SendDob,SendAge,
                    SendGender,SendCity,SendArea,SendPincode,SendState,SendAddress);
            return soapObject;
        }

        @Override
        protected void onPostExecute(SoapObject soapObject) {
            super.onPostExecute(soapObject);
            try{
                if(pDialog != null && pDialog.isShowing())
                    pDialog.dismiss();

                if(soapObject == null){
                    Log.e(getLocalClassName(),soapObject.toString());
                }else{
                    Log.e(getLocalClassName(),soapObject.toString());
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    class PasswordAsync extends AsyncTask<Void,Void,SoapObject>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            try{
                if(pDialog != null && !pDialog.isShowing())
                    pDialog.show();

            }catch (Exception e){
                Log.e(getLocalClassName(),e.getMessage());
            }
        }

        @Override
        protected SoapObject doInBackground(Void... params) {

            Webservices webservices=new Webservices();
            SoapObject soapObject=webservices.PASSWORD(SendContact,SendContact,"LAB","LAB",SendContact);
            return soapObject;
        }

        @Override
        protected void onPostExecute(SoapObject soapObject) {
            super.onPostExecute(soapObject);
            try{
                if(pDialog != null && pDialog.isShowing())
                    pDialog.dismiss();

                if(soapObject == null){
                    Log.e(getLocalClassName(),soapObject.toString());
                }else{
                    Log.e(getLocalClassName(),soapObject.toString());
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    class CheckOtpAsyn extends AsyncTask<String ,Void,SoapObject>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            try{
                if(pDialog != null && !pDialog.isShowing())
                    pDialog.show();

            }catch (Exception e){
                Log.e(getLocalClassName(),e.getMessage());
            }
        }

        @Override
        protected SoapObject doInBackground(String... params) {

            Webservices webservices=new Webservices();
            SoapObject soapObject=webservices.CHECK_OTP(params[0],params[1]);
            return soapObject;

        }
        @Override
        protected void onPostExecute(SoapObject soapObject) {
            super.onPostExecute(soapObject);
            try{
                if(pDialog != null && pDialog.isShowing())
                    pDialog.dismiss();

                if(soapObject == null){
                    Log.e(getLocalClassName(),soapObject.toString());
                }else{
                    Log.e(getLocalClassName(),soapObject.toString());

                    OTPFlag=soapObject.getProperty("CHKOTPResult").toString();
                    Log.e("OTPFlag",OTPFlag);

                    //here 0 comes means otp not match
                    //here 1 come means it match
                    if(OTPFlag.trim().equals("1")){
                        //it 1 means
                        dialog.dismiss();

                        UpdatePasswordDialog();

                    }else{
                        customeToast.CustomeToastSetting(Register.this,"OTP is Not Correct");
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    class UpdatePasswordAsyn extends AsyncTask<String,Void,SoapObject>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            try{
                if(progressDialog != null && !progressDialog.isShowing())
                    progressDialog.show();

            }catch (Exception e){
                e.printStackTrace();
            }
        }

        @Override
        protected SoapObject doInBackground(String... params) {
            Webservices webservices=new Webservices();
            SoapObject soapObject=webservices.UpdatePassword(params[0],params[1]);
            return soapObject;
        }

        @Override
        protected void onPostExecute(SoapObject soapObject) {
            super.onPostExecute(soapObject);
            try{
                if(progressDialog != null && progressDialog.isShowing()){
                    progressDialog.dismiss();
                }
                //dismiss the dialog
                UpdatePasswordDialog.dismiss();
                //print massege on Toast
                customeToast.CustomeToastSetting(Register.this,"Registration Succesfully");
                //show the massege in toast
                //customeToast.CustomeToastSetting(Register.this,"Password Updated Succesfully");
                //jump on the Login Activity
                startActivity(new Intent(Register.this,Login.class));
                Register.this.finish();

                if(soapObject == null){
                    Log.e(getLocalClassName(),soapObject.toString());
                }else{
                    Log.e(getLocalClassName(),soapObject.toString());
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
