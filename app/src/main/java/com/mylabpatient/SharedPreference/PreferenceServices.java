package com.mylabpatient.SharedPreference;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

/**
 * Created by Sunil on 1/25/2017.
 */

public class PreferenceServices {

    public static PreferenceServices mInstance;
    private static Context mContext;
    public static final String TAG="MylabPatient";
    public static final String DEFAULT = "NoData";

    public static final String PatientId="PatientId";
    public static final String PatientName="PatientName";
    public static final String Contact="Contact";
    public static final String Email="Email";
    public static final String Password="Password";
    public static final String Dob="Dob";
    public static final String Gender="Gender";
    public static final String City="City";
    public static final String Area="Area";
    public static final String Pincode="Pincode";
    public static final String State="State";
    public static final String Address="Address";
    public static final String Age="Age";
    public static final String RegDate="RegDate";
    public static final String RowSent="RowSent";

    public static final String LoginStatus="LoginStatus";


    public PreferenceServices(Context context) {

        mContext=context;
    }

    public static synchronized PreferenceServices getInstance(Context context) {
        if(mInstance == null)
        {
            mContext=context;
            mInstance= new PreferenceServices(context);
            return mInstance;
        }
        return mInstance;
    }

    public SharedPreferences getpref()
    {
        SharedPreferences preferences=mContext.getSharedPreferences(TAG,Context.MODE_PRIVATE);
        return preferences;
    }

    public void saveLoginDetails(String PatientId,String PatientName,String Contact,String Email,String Password,String Dob,String Gender,
                                 String City,String Area,String Pincode,String State,String Address,String Age,String RegDate,String RowSent) {
        SharedPreferences.Editor editor=getpref().edit();
        editor.putString(this.PatientId,PatientId);
        editor.putString(this.PatientName,PatientName);
        editor.putString(this.Contact,Contact);
        editor.putString(this.Email,Email);
        editor.putString(this.Password,Password);
        editor.putString(this.Dob,Dob);
        editor.putString(this.Gender,Gender);
        editor.putString(this.City,City);
        editor.putString(this.Area,Area);
        editor.putString(this.Pincode,Pincode);
        editor.putString(this.State,State);
        editor.putString(this.Address,Address);
        editor.putString(this.Age,Age);
        editor.putString(this.RegDate,RegDate);
        editor.putString(this.RowSent,RowSent);
        editor.commit();
    }

    public HashMap<String,String> getLoginDetails() {
        HashMap<String,String> map=new HashMap<>();
        map.put(PatientId,getpref().getString(PatientId,DEFAULT));
        map.put(PatientName,getpref().getString(PatientName,DEFAULT));
        map.put(Contact,getpref().getString(Contact,DEFAULT));
        map.put(Email,getpref().getString(Email,DEFAULT));
        map.put(Password,getpref().getString(Password,DEFAULT));
        map.put(Dob,getpref().getString(Dob,DEFAULT));
        map.put(Gender,getpref().getString(Gender,DEFAULT));
        map.put(City,getpref().getString(City,DEFAULT));
        map.put(Area,getpref().getString(Area,DEFAULT));
        map.put(Pincode,getpref().getString(Pincode,DEFAULT));
        map.put(State,getpref().getString(State,DEFAULT));
        map.put(Address,getpref().getString(Address,DEFAULT));
        map.put(Age,getpref().getString(Age,DEFAULT));
        map.put(RegDate,getpref().getString(RegDate,DEFAULT));
        map.put(RowSent,getpref().getString(RowSent,DEFAULT));
        return map;
    }

    public void saveUserLoginStatus(String loginStatus) {
        SharedPreferences.Editor editor = getpref().edit();
        editor.putString(LoginStatus, loginStatus);
        editor.commit();
    }

    public String getUserLoginStatus() {
        return getpref().getString(LoginStatus, "false");
    }

}
