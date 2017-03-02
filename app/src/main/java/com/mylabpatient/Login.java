package com.mylabpatient;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mylabpatient.ConstantClasses.CustomeToast;
import com.mylabpatient.ConstantClasses.NetworkCheck;
import com.mylabpatient.SharedPreference.PreferenceServices;
import com.mylabpatient.WebServices.Webservices;

import org.ksoap2.serialization.SoapObject;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class Login extends AppCompatActivity implements View.OnClickListener{

    EditText edtusername,edtpass;
    TextView txtnewregister,txtforgetpass;
    Button btnLogin;
    SweetAlertDialog progressDialog;
    String Username, password;
    CustomeToast customeToast=new CustomeToast();
    NetworkCheck networkCheck;
    Dialog forgetpassdialog,dialog,UpdatePasswordDialog;
    //String for Data Which is Comming From Server
    String PatientId,PatientName,Contact,Email,Password,Dob,Gender,City,Area,
            Pincode,State,Address,Age,RegDate,RowSent;
    //these use for forget password
    String SendName,SendContact,SendEmail,OTPFlag="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Login);
        setContentView(R.layout.activity_login);

        //initializing the progress dialog
        progressDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        progressDialog.getProgressHelper().setBarColor(Color.parseColor("#EF6C00"));
        progressDialog.setTitleText("Please Wait..");
        progressDialog.setCancelable(false);

        //initializing the network constatn class
        networkCheck=new NetworkCheck(this);

        //all initializing doing in this method
        init();

    }

    private void init() {
        edtusername=(EditText)findViewById(R.id.edt_user_name);
        edtpass=(EditText)findViewById(R.id.edt_password);
        txtnewregister=(TextView)findViewById(R.id.txtLoginRegister);
        txtforgetpass=(TextView)findViewById(R.id.txtLoginForgetPass);
        btnLogin=(Button)findViewById(R.id.btn_login);

        txtnewregister.setOnClickListener(this);
        txtforgetpass.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
    }
    private void ForgetPasswordDialogMethod() {

        forgetpassdialog=new Dialog(Login.this);
        forgetpassdialog.setContentView(R.layout.forgetpassword);
        forgetpassdialog.setCancelable(false);
        forgetpassdialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        forgetpassdialog.setCanceledOnTouchOutside(false);
        ImageView imageView=(ImageView)forgetpassdialog.findViewById(R.id.forgetPassclosedialog);
        Button btnSubmit=(Button)forgetpassdialog.findViewById(R.id.btnforgetPassSubmit);
        final EditText edtusernameforgetPass=(EditText)forgetpassdialog.findViewById(R.id.edtforgetPassusername);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(edtusernameforgetPass.getText().toString().length() == 0 &&
                        edtusernameforgetPass.getText().toString().equals("")){
                    edtusernameforgetPass.setError("Enter UserName Or Mobile Number");
                }else{
                    SendName=edtusernameforgetPass.getText().toString();
                    SendContact=edtusernameforgetPass.getText().toString();
                    SendEmail=edtusernameforgetPass.getText().toString();

                    //calling the forget password webservice for massege
                    new ForgetPasswordAsync().execute();
                }

            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forgetpassdialog.dismiss();
            }
        });

        forgetpassdialog.show();
    }
    private void OTPDialog() {

        dialog=new Dialog(Login.this);
        dialog.setContentView(R.layout.otpdialoglayout);
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCanceledOnTouchOutside(false);
        ImageView imageView=(ImageView)dialog.findViewById(R.id.otpclosedialog);
        Button btnSubmit=(Button)dialog.findViewById(R.id.btnOtpSubmit);
        final EditText edtOtp=(EditText)dialog.findViewById(R.id.edtOTPForRegistration);

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

        UpdatePasswordDialog=new Dialog(Login.this);
        UpdatePasswordDialog.setContentView(R.layout.newpasswordtaking_registration);
        UpdatePasswordDialog.setCancelable(false);
        UpdatePasswordDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        UpdatePasswordDialog.setCanceledOnTouchOutside(false);
        ImageView imageView=(ImageView)UpdatePasswordDialog.findViewById(R.id.passwordclosedialog);
        Button btnSubmit=(Button)UpdatePasswordDialog.findViewById(R.id.btnupdatePasssubmit);
        final EditText edtpassword=(EditText)UpdatePasswordDialog.findViewById(R.id.edtPasswordREgistration);
        final EditText edtconfirmPass=(EditText)UpdatePasswordDialog.findViewById(R.id.edtConfirmPasswordREgistration);

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
                    customeToast.CustomeToastSetting(Login.this,"Password Not Match");
                }else{
                    new UpdatePasswordAsyn().execute(SendContact,edtpassword.getText().toString());
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
    private void sendindData(){
        if(edtusername.getText().toString().equals("")
                && edtusername.getText().toString().length() == 0){
            edtusername.setError("Enter UserName");
        }else if(edtpass.getText().toString().equals("")
                && edtpass.getText().toString().length() ==0){
            edtpass.setError("Enter Password");
        }else{
            Username=edtusername.getText().toString();
            password=edtpass.getText().toString();

            new LoginAsyn().execute();
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.txtLoginRegister:
                startActivity(new Intent(Login.this,Register.class));
                finish();
                break;

            case R.id.txtLoginForgetPass:
                //startActivity(new Intent(Login.this,ForgetPass.class));
                //finish();
                ForgetPasswordDialogMethod();
                break;

            case R.id.btn_login:
                if(networkCheck.isConnectinToInternet()){
                    //sending Data
                    sendindData();
                }else{
                    customeToast.CustomeToastSetting(Login.this,"InterNet Conntection Required");
                }
                break;
        }
    }

    class LoginAsyn extends AsyncTask<Void,Void,SoapObject>{

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
            SoapObject soapObject=webservices.PatientLogin(Username,Username,password);
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
                    Log.e("Data",soapObject.toString());

                    SoapObject soap = (SoapObject) soapObject.getProperty(0);
                    System.out.println("soap : : "+ soap.getPropertyCount() +"  "+ soap.toString() );

                    SoapObject res = (SoapObject) soap.getProperty(0);
                    System.out.println("res : : "+ res.getPropertyCount() +"  "+ res.toString() );

                    SoapObject res2 = (SoapObject) soap.getProperty(1);
                    System.out.println("res2 : : "+ res2.getPropertyCount() +"  "+ res2.toString() );

                    SoapObject last = (SoapObject) res2.getProperty(0);
                    System.out.println("last : : "+ last.getPropertyCount() +"  "+ last.toString() );

                    SoapObject last2 = (SoapObject) last.getProperty(0);
                    System.out.println("last2 : : "+ last2.getPropertyCount() +"  "+ last2.toString());

                    PatientId=last2.getProperty("Patient_id").toString();
                    PatientName=last2.getProperty("Patient_name").toString();
                    Contact=last2.getProperty("Contact_no").toString();
                    Email=last2.getProperty("Email").toString();
                    Password=last2.getProperty("Password").toString();
                    Dob=last2.getProperty("DOB").toString();
                    Gender=last2.getProperty("Gender").toString();
                    City=last2.getProperty("City").toString();
                    Area=last2.getProperty("Area").toString();
                    Pincode=last2.getProperty("Pincode").toString();
                    State=last2.getProperty("State").toString();
                    Address=last2.getProperty("Address").toString();
                    Age=last2.getProperty("age").toString();
                    RegDate=last2.getProperty("RegDate").toString();
                    RowSent=last2.getProperty("RowSent").toString();

                    //save it on shared preference
                    PreferenceServices.getInstance(Login.this).saveLoginDetails(PatientId,PatientName,Contact,Email,Password,Dob,
                            Gender,City,Area,Pincode,State,Address,Age,RegDate,RowSent);

                    //making the login status true
                    PreferenceServices.getInstance(Login.this).saveUserLoginStatus("true");

                    //start the main activity
                    startActivity(new Intent(Login.this,Home.class));

                    //Toast.makeText(Login.this,"Succesfully Login Mr. "+PatientName,Toast.LENGTH_LONG).show();
                    customeToast.CustomeToastSetting(Login.this,"Succesfully Login Mr. "+PatientName);

                }
            }catch (Exception e){
                customeToast.CustomeToastSetting(Login.this,"UserName And Password Wrong");
                System.out.print(e.getMessage());
                e.printStackTrace();

            }
        }
    }
    class ForgetPasswordAsync extends AsyncTask<Void,Void,SoapObject>{

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
            SoapObject soapObject=webservices.FORGETPASSWROD(SendName,SendContact,"LAB","LAB",SendEmail);
            return soapObject;
        }

        @Override
        protected void onPostExecute(SoapObject soapObject) {
            super.onPostExecute(soapObject);
            try{
                if(progressDialog != null && progressDialog.isShowing())
                    progressDialog.dismiss();

                //after sedning sms to device forget password dialog hide
                forgetpassdialog.dismiss();
                //and visiblae the OTP Dialog
                OTPDialog();

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
                if(progressDialog != null && !progressDialog.isShowing())
                    progressDialog.show();

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
                if(progressDialog != null && progressDialog.isShowing())
                    progressDialog.dismiss();

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
                        //when password is right give option to insert new passsword
                        UpdatePasswordDialog();
                    }else{
                        customeToast.CustomeToastSetting(Login.this,"OTP is Not Correct");
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
                //already in login actitivyt just close all dialog
                //dismiss the dialog
                UpdatePasswordDialog.dismiss();
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
