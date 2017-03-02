package com.mylabpatient;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.mylabpatient.ConstantClasses.CustomeToast;
import com.mylabpatient.ConstantClasses.NetworkCheck;
import com.mylabpatient.ConstantClasses.emailValidator;
import com.mylabpatient.SharedPreference.PreferenceServices;
import com.mylabpatient.WebServices.Webservices;

import org.ksoap2.serialization.SoapObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class Profile extends AppCompatActivity implements View.OnClickListener{

    EditText edtName,edtContact,edtEmail,edtDob,edtCity,edtArea,edtPincode,edtState,edtAddress;
    RadioGroup GenderRbg;
    RadioButton rdbMale,rdbFemale;
    Button btnUpdate;
    SweetAlertDialog pDialog;
    NetworkCheck networkCheck;
    CustomeToast customeToast=new CustomeToast();
    com.mylabpatient.ConstantClasses.emailValidator emailValidator=new emailValidator();
    public static HashMap<String,String> map=new HashMap<>();
    String SendName,SendContact,SendEmail,SendDob,SendCity,SendArea,SendPincode,
            SendState,SendAddress,SendGender,SendPatientContact;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setting the new theme in which no action bar
        setTheme(R.style.Login);
        setContentView(R.layout.activity_profile);

        //getting data in map from shared Preferences
        map= PreferenceServices.getInstance(Profile.this).getLoginDetails();

        pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#EF6C00"));
        pDialog.setTitleText("Loading");
        pDialog.setCancelable(false);

        //initzialing the widget here
        init();

        //initializing the network check
        networkCheck=new NetworkCheck(Profile.this);

        //setting the Data Which is Already Store in Shared Preference
        settingData();


    }

    private void init(){

        edtName=(EditText)findViewById(R.id.edtProfileName);
        edtContact=(EditText)findViewById(R.id.edtProfileContact);
        edtEmail=(EditText)findViewById(R.id.edtProfileEmail);
        edtDob=(EditText)findViewById(R.id.edtProfileDob);
        edtCity=(EditText)findViewById(R.id.edtProfileCity);
        edtArea=(EditText)findViewById(R.id.edtProfileArea);
        edtPincode=(EditText)findViewById(R.id.edtProfilePincode);
        edtState=(EditText)findViewById(R.id.edtProfileState);
        edtAddress=(EditText)findViewById(R.id.edtProfileAddress);

        GenderRbg=(RadioGroup)findViewById(R.id.ProfileGenderRdg);

        rdbMale=(RadioButton)findViewById(R.id.ProfileRbdMale);
        rdbFemale=(RadioButton)findViewById(R.id.ProfileRbdFemail);

        btnUpdate=(Button)findViewById(R.id.btnProfileUpdate);
        btnUpdate.setOnClickListener(this);


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
                new DatePickerDialog(Profile.this,date,calendar.get(Calendar.YEAR),
                        calendar.get(calendar.MONTH),calendar.get(calendar.DAY_OF_MONTH)).show();
            }
        });
    }
    private void settingData(){

        edtName.setText(map.get(PreferenceServices.PatientName));
        edtContact.setText(map.get(PreferenceServices.Contact));
        edtEmail.setText(map.get(PreferenceServices.Email));
        edtDob.setText(map.get(PreferenceServices.Dob));
        edtCity.setText(map.get(PreferenceServices.City));
        edtArea.setText(map.get(PreferenceServices.Area));
        edtPincode.setText(map.get(PreferenceServices.Pincode));
        edtState.setText(map.get(PreferenceServices.State));
        edtAddress.setText(map.get(PreferenceServices.Address));

        if(map.get(PreferenceServices.Gender).trim().equals("Male")){
            rdbMale.setChecked(true);
        }else{
            rdbFemale.setChecked(true);
        }
    }
    private void SendingData(){
        if(edtName.getText().toString().length() == 0 && edtName.getText().toString().equals("")){
            edtName.setError("Enter Your Name");
        }else if(edtContact.getText().toString().length() == 0 && edtContact.getText().toString().equals("")){
            edtContact.setError("Enter Your Contact");
        }else if(edtEmail.getText().toString().length() == 0 && edtEmail.getText().toString().equals("")){
            edtEmail.setError("Enter Your Email");
        }else if(!emailValidator.emailValidatorcheck(edtEmail.getText().toString())){
            edtEmail.setError("Enter Propet Email Id");
        }else if(edtDob.getText().toString().length() == 0 && edtDob.getText().toString().equals("")){
            edtDob.setError("Enter Your DOB");
        }else if(edtCity.getText().toString().length() == 0 && edtCity.getText().toString().equals("")){
            edtCity.setError("Enter Your City");
        }else if(edtArea.getText().toString().length() == 0 && edtArea.getText().toString().equals("")){
            edtArea.setError("Enter Your Area");
        }else if(edtPincode.getText().toString().length() == 0 && edtPincode.getText().toString().equals("")){
            edtPincode.setError("Enter Your Pincode");
        }else if(edtState.getText().toString().length() == 0 && edtState.getText().toString().equals("")){
            edtState.setError("Enter Your State");
        }else if(edtAddress.getText().toString().length() == 0 && edtAddress.getText().toString().equals("")){
            edtAddress.setError("Enter Your Address");
        }else if(!rdbMale.isChecked() && !rdbFemale.isChecked()){
            customeToast.CustomeToastSetting(Profile.this,"Select Gender");
        }else{
            SendName=edtName.getText().toString();
            SendContact=edtContact.getText().toString();
            SendEmail=edtEmail.getText().toString();
            SendDob=edtDob.getText().toString();
            SendCity=edtCity.getText().toString();
            SendArea=edtArea.getText().toString();
            SendPincode=edtPincode.getText().toString();
            SendState=edtState.getText().toString();
            SendAddress=edtAddress.getText().toString();
            if(rdbMale.isChecked()){
                SendGender="Male";
            }else{
                SendGender="Female";
            }
            SendPatientContact=map.get(PreferenceServices.Contact);

            if(networkCheck.isConnectinToInternet()){
                new ProfileUpdateAsyn().execute();
            }else{
                customeToast.CustomeToastSetting(Profile.this,"Internet Connection Required");
            }
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btnProfileUpdate:
                SendingData();
                break;
        }
    }
    class ProfileUpdateAsyn extends AsyncTask<Void,Void,SoapObject>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            try{
                if(pDialog != null && !pDialog.isShowing())
                    pDialog.show();

            }catch (Exception e){
                e.printStackTrace();
            }
        }

        @Override
        protected SoapObject doInBackground(Void... params) {
            Webservices webservices=new Webservices();
            SoapObject soapObject=webservices.patientupdateprofile(SendName,SendContact,SendEmail,SendDob,SendGender,
                    SendCity,SendArea,SendPincode,SendState,SendAddress,SendContact);

            //Update in Shared Preference As well
            PreferenceServices.getInstance(Profile.this).saveLoginDetails(map.get(PreferenceServices.PatientId),SendName,
                    map.get(PreferenceServices.Contact),map.get(PreferenceServices.Email),map.get(PreferenceServices.Password),SendDob,
                    SendGender,SendCity,SendArea,SendPincode,SendState,SendAddress,map.get(PreferenceServices.Age),
                    map.get(PreferenceServices.RegDate),map.get(PreferenceServices.RowSent));
            return soapObject;
        }

        @Override
        protected void onPostExecute(SoapObject soapObject) {
            super.onPostExecute(soapObject);
            try{
                if(pDialog != null && pDialog.isShowing())
                    pDialog.dismiss();

                //after update jump on home activity
                startActivity(new Intent(Profile.this,Home.class));
                Profile.this.finish();

                customeToast.CustomeToastSetting(Profile.this,"You Need To re Login To get Info Update");

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
