package com.mylabpatient.WebServices;

import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.sax2.Driver;

import java.io.IOException;

/**
 * Created by Sunil on 2/25/2017.
 */

public class Webservices {

    private final String URL="http://mypathtest.com/myLabService/WebService1.asmx";
    private final String NAMESPACE="http://tempuri.org/";

    //Previous Home Visit
    private final String SOAP_LOGIN="http://tempuri.org/patientLogin";
    private final String METHOD_NAME_LOGIN="patientLogin";



    //previous Home visit
    public SoapObject PatientLogin(String contact,String email,String password) {

        SoapObject result=null;

        SoapObject request=new SoapObject(NAMESPACE,METHOD_NAME_LOGIN);

        request.addProperty("contact_no",contact);
        request.addProperty("Email",email);
        request.addProperty("Password",password);

        SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        envelope.dotNet=true;

        HttpTransportSE httpTransportSE=new HttpTransportSE(URL);

        try {
            httpTransportSE.call(SOAP_LOGIN,envelope);

            result = (SoapObject) envelope.bodyIn;

            return result;


        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        return null;
    }

    //Previous Home Visit
    private final String SOAP_ACTION_PREVIOUS_HOMEVISIT="http://tempuri.org/patient_booked_list_bind";
    private final String METHOD_NAME_PREVIOUS_HOMEVISIT="patient_booked_list_bind";

   //previous Home visit
    public SoapObject PreviousHomeVisit(String contact) {

        SoapObject result=null;

        SoapObject request=new SoapObject(NAMESPACE,METHOD_NAME_PREVIOUS_HOMEVISIT);

        request.addProperty("PatientContact",contact);

        SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        envelope.dotNet=true;

        HttpTransportSE httpTransportSE=new HttpTransportSE(URL);

        try {
            httpTransportSE.call(SOAP_ACTION_PREVIOUS_HOMEVISIT,envelope);

            result = (SoapObject) envelope.bodyIn;

            return result;


        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        return null;
    }

    //cancel home visits
    private final String SOAP_ACTION_CANCEL_HOMEVISIT="http://tempuri.org/patient_cancel_HomeVisit_list_bind";
    private final String METHOD_NAME_CANCEL_HOMEVISIT="patient_cancel_HomeVisit_list_bind";

    //cancel home visits
    public SoapObject CancelHomeVisit(String ID) {

        SoapObject result=null;

        SoapObject request=new SoapObject(NAMESPACE,METHOD_NAME_CANCEL_HOMEVISIT);

        request.addProperty("id",ID);

        SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        envelope.dotNet=true;

        HttpTransportSE httpTransportSE=new HttpTransportSE(URL);

        try {
            httpTransportSE.call(SOAP_ACTION_CANCEL_HOMEVISIT,envelope);

            try{
                result = (SoapObject) envelope.bodyIn;
            }catch (Exception e){}


            return result;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        return null;
    }

    //getting all state
    private final String SOAP_GET_ALLSTATE="http://tempuri.org/getStateCity";
    private final String METHOD_GET_ALLSTATE="getStateCity";

    //getting all state
    public SoapObject GETAllSTATE() {

        SoapObject result=null;

        SoapObject request=new SoapObject(NAMESPACE,METHOD_GET_ALLSTATE);

        SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        envelope.dotNet=true;

        HttpTransportSE httpTransportSE=new HttpTransportSE(URL);

        try {
            httpTransportSE.call(SOAP_GET_ALLSTATE,envelope);

            result = (SoapObject) envelope.bodyIn;

            return result;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        return null;
    }

    //getting city according to state
    private final String SOAP_GET_ALLCITY="http://tempuri.org/getCity";
    private final String METHOD_GET_ALLCITY="getCity";

    //getting all state
    public SoapObject GETAllCITYACCORDINGASTATE(String state) {

        SoapObject result=null;

        SoapObject request=new SoapObject(NAMESPACE,METHOD_GET_ALLCITY);

        request.addProperty("state",state);

        SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        envelope.dotNet=true;

        HttpTransportSE httpTransportSE=new HttpTransportSE(URL);

        try {
            httpTransportSE.call(SOAP_GET_ALLCITY,envelope);

            result = (SoapObject) envelope.bodyIn;

            return result;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        return null;
    }

    //Previous appoinment
    private final String SOAP_ACTION_PREVIOUS_APPOINMENT="http://tempuri.org/patient_booked_Appoint_list_bind";
    private final String METHOD_NAME_PREVIOUS_APPOINMENT="patient_booked_Appoint_list_bind";

    //previous appoinment
    public SoapObject PreviousAppoinment(String contact) {

        SoapObject result=null;

        SoapObject request=new SoapObject(NAMESPACE,METHOD_NAME_PREVIOUS_APPOINMENT);

        request.addProperty("PatientContact",contact);

        SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        envelope.dotNet=true;

        HttpTransportSE httpTransportSE=new HttpTransportSE(URL);

        try {
            httpTransportSE.call(SOAP_ACTION_PREVIOUS_APPOINMENT,envelope);

            result = (SoapObject) envelope.bodyIn;

            return result;


        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        return null;
    }

    //Cancel Appoinment
    private final String SOAP_ACTION_CANCEL_APPOINMENT="http://tempuri.org/patient_cancel_Appoint_list_bind";
    private final String METHOD_NAME_CANCEL_APPOINMENT="patient_cancel_Appoint_list_bind";

    //Cancel Appoinment
    public SoapObject CancelAppoinment(String Id) {

        SoapObject result=null;

        SoapObject request=new SoapObject(NAMESPACE,METHOD_NAME_CANCEL_APPOINMENT);

        request.addProperty("id",Id);

        SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        envelope.dotNet=true;

        HttpTransportSE httpTransportSE=new HttpTransportSE(URL);

        try {
            httpTransportSE.call(SOAP_ACTION_CANCEL_APPOINMENT,envelope);

            result = (SoapObject) envelope.bodyIn;

            return result;


        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        return null;
    }

    //UpComming Events
    private final String SOAP_ACTION_UPCOMMING_EVENTS="http://tempuri.org/patiend_bind_packkage";
    private final String METHOD_NAME_UPCOMMING_EVENTS="patiend_bind_packkage";

    //UpComming Events
    public SoapObject UpCommingEvents(String flag) {

        SoapObject result=null;

        SoapObject request=new SoapObject(NAMESPACE,METHOD_NAME_UPCOMMING_EVENTS);

        request.addProperty("flag",flag);

        SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        envelope.dotNet=true;

        HttpTransportSE httpTransportSE=new HttpTransportSE(URL);

        try {
            httpTransportSE.call(SOAP_ACTION_UPCOMMING_EVENTS,envelope);

            result = (SoapObject) envelope.bodyIn;

            return result;


        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        return null;
    }

    //Lab Details
    private final String SOAP_ACTION_LABNAME="http://tempuri.org/labname";
    private final String METHOD_NAME_LABNAME="labname";

    //UpComming Events
    public SoapObject LabDetails() {

        SoapObject result=null;

        SoapObject request=new SoapObject(NAMESPACE,METHOD_NAME_LABNAME);

        SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        envelope.dotNet=true;

        HttpTransportSE httpTransportSE=new HttpTransportSE(URL);

        try {
            httpTransportSE.call(SOAP_ACTION_LABNAME,envelope);

            result = (SoapObject) envelope.bodyIn;

            return result;


        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        return null;
    }

    //Check Patient Exists
    private final String SOAP_CHECK_PATIENTEXITS="http://tempuri.org/CHKExistPatient";
    private final String METHOD_CHECK_PATIENTEXITS="CHKExistPatient";

    //Check Patient Exists
    public SoapObject CHECK_PATIENTEXITS(String contact,String Email) {

        SoapObject result=null;

        SoapObject request=new SoapObject(NAMESPACE,METHOD_CHECK_PATIENTEXITS);

        request.addProperty("contact_no",contact);
        request.addProperty("email",Email);

        SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        envelope.dotNet=true;

        HttpTransportSE httpTransportSE=new HttpTransportSE(URL);

        try {
            httpTransportSE.call(SOAP_CHECK_PATIENTEXITS,envelope);

            result = (SoapObject) envelope.bodyIn;

            return result;


        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        return null;
    }

    //Insert New Patient
    private final String SOAP_INSERT_PATIENT_PORTAL="http://tempuri.org/Insert_PatientPortal";
    private final String METHOD_INSERT_PATIENT_PORTAL="Insert_PatientPortal";

    //Insert New Patient
    public SoapObject INSERT_PATIENT_PORTAL(String name,String contact,String Email,String Dob,String age,String gender,
                                            String city,String Area,String pincode,String State,String address) {

        SoapObject result=null;

        SoapObject request=new SoapObject(NAMESPACE,METHOD_INSERT_PATIENT_PORTAL);

        request.addProperty("Patient_name",name);
        request.addProperty("Contact_no",contact);
        request.addProperty("Email",Email);
        request.addProperty("DOB",Dob);
        request.addProperty("age",age);
        request.addProperty("Gender",gender);
        request.addProperty("City",city);
        request.addProperty("Area",Area);
        request.addProperty("Pincode",pincode);
        request.addProperty("State",State);
        request.addProperty("address",address);

        SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        envelope.dotNet=true;

        HttpTransportSE httpTransportSE=new HttpTransportSE(URL);

        try {
            httpTransportSE.call(SOAP_INSERT_PATIENT_PORTAL,envelope);

            result = (SoapObject) envelope.bodyIn;

            return result;


        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        return null;
    }

    //GET max Appoinment id
    private final String SOAP_MAX_APPOINMENT_ID="http://tempuri.org/MaxAppointmentId";
    private final String METHOD_MAX_APPOINMENT_ID="MaxAppointmentId";

    //GET max Appoinment id
    public SoapObject MAX_APPOINMENT_ID() {

        SoapObject result=null;

        SoapObject request=new SoapObject(NAMESPACE,METHOD_MAX_APPOINMENT_ID);

        SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        envelope.dotNet=true;

        HttpTransportSE httpTransportSE=new HttpTransportSE(URL);

        try {
            httpTransportSE.call(SOAP_MAX_APPOINMENT_ID,envelope);

            result = (SoapObject) envelope.bodyIn;

            return result;


        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        return null;
    }

    //GET max HOMEVISITS id
    private final String SOAP_MAX_HOMEVISIT_ID="http://tempuri.org/MaxIdFromHomeVisit";
    private final String METHOD_MAX_HOMEVISIT_ID="MaxIdFromHomeVisit";

    //GET max HOMEVISITS id
    public SoapObject MAX_HOMEVISIT_ID() {

        SoapObject result=null;

        SoapObject request=new SoapObject(NAMESPACE,METHOD_MAX_HOMEVISIT_ID);

        SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        envelope.dotNet=true;

        HttpTransportSE httpTransportSE=new HttpTransportSE(URL);

        try {
            httpTransportSE.call(SOAP_MAX_HOMEVISIT_ID,envelope);

            result = (SoapObject) envelope.bodyIn;

            return result;


        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        return null;
    }

    //insert new Homevisit
    private final String SOAP_NEW_HOMEVISIT="http://tempuri.org/PatientHomeVisit";
    private final String METHOD_NEW_HOMEVISIT="PatientHomeVisit";

    //GET max HOMEVISITS id
    public SoapObject NEW_HOMEVISIT(String SendEmail,String SendPatientContact,String SendDate,String SendTime,String SendContact,
                                    String SendArea,String SendAddress,String SendPreferLab,String SendRemark,String SendFileName,
                                    String SendFilePath,String SendName,String SendId,String SendBookedFlag,String SendLabCode,
                                    String SendVisitId) {

        SoapObject result=null;

        SoapObject request=new SoapObject(NAMESPACE,METHOD_NEW_HOMEVISIT);

        request.addProperty("patient_email",SendEmail);
        request.addProperty("patient_contact",SendPatientContact);
        request.addProperty("DATE",SendDate);
        request.addProperty("TIME",SendTime);
        request.addProperty("CONTACT",SendContact);
        request.addProperty("AREA",SendArea);
        request.addProperty("ADDRESS",SendAddress);
        request.addProperty("PREFERED_LAB",SendPreferLab);
        request.addProperty("REMARK",SendRemark);
        request.addProperty("FILENAME",SendFileName);
        request.addProperty("FILEPATH",SendFilePath);
        request.addProperty("name",SendName);
        request.addProperty("id",SendId);
        request.addProperty("BOOKED_FLAG",SendBookedFlag);
        request.addProperty("LabCode",SendLabCode);
        request.addProperty("VisitID",SendVisitId);

        SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        envelope.dotNet=true;

        HttpTransportSE httpTransportSE=new HttpTransportSE(URL);

        try {
            httpTransportSE.call(SOAP_NEW_HOMEVISIT,envelope);

            result = (SoapObject) envelope.bodyIn;

            return result;


        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        return null;
    }

    //insert new Homevisit Sending Massege
    private final String SOAP_NEW_HOMEVISIT_SENDING_MASSAGE="http://tempuri.org/Homevisitmsg";
    private final String METHOD_NEW_HOMEVISIT_SENDING_MASSAGE="Homevisitmsg";

    //insert new Homevisit Sending Massege
    public SoapObject NEW_HOMEVISIT_SENDING_MASSAGE(String contactnew,String PatientContact,String labname,String labcontact) {

        SoapObject result=null;

        SoapObject request=new SoapObject(NAMESPACE,METHOD_NEW_HOMEVISIT_SENDING_MASSAGE);

        request.addProperty("contactn",contactnew);
        request.addProperty("patient_contact",PatientContact);
        request.addProperty("LabName",labname);
        request.addProperty("labContact",labcontact);

        SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        envelope.dotNet=true;

        HttpTransportSE httpTransportSE=new HttpTransportSE(URL);

        try {
            httpTransportSE.call(SOAP_NEW_HOMEVISIT_SENDING_MASSAGE,envelope);

            result = (SoapObject) envelope.bodyIn;

            return result;


        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        return null;
    }

    //insert new Appoinment
    private final String SOAP_NEW_NEW_APPOINMENT="http://tempuri.org/patientAppointment";
    private final String METHOD_NEW_NEW_APPOINMENT="patientAppointment";

    //insert new Appoinment
    public SoapObject NEW_APPOINMENT(String SendPatientContact,String SendEmail,String SendDate,String SendTime,String SendContact,
                                    String SendArea,String SendAddress,String SendPreferLab,String SendRemark,String SendFileName,
                                    String SendFilePath,String SendName,String SendId,String SendBookedFlag,String SendAge,String SendLabCode,
                                    String SendVisitId) {

        SoapObject result=null;

        SoapObject request=new SoapObject(NAMESPACE,METHOD_NEW_NEW_APPOINMENT);

        request.addProperty("patient_contact",SendPatientContact);
        request.addProperty("patient_email",SendEmail);
        request.addProperty("DATE",SendDate);
        request.addProperty("TIME",SendTime);
        request.addProperty("CONTACT",SendContact);
        request.addProperty("AREA",SendArea);
        request.addProperty("ADDRESS",SendAddress);
        request.addProperty("PREFERED_LAB",SendPreferLab);
        request.addProperty("REMARK",SendRemark);
        request.addProperty("FILENAME",SendFileName);
        request.addProperty("FILEPATH",SendFilePath);
        request.addProperty("USERNAME_Book",SendName);
        request.addProperty("PATIENT_ID",SendId);
        request.addProperty("FLAG",SendBookedFlag);
        request.addProperty("age",SendAge);
        request.addProperty("LabCode",SendLabCode);
        request.addProperty("AppointID",SendVisitId);

        SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        envelope.dotNet=true;

        HttpTransportSE httpTransportSE=new HttpTransportSE(URL);

        try {
            httpTransportSE.call(SOAP_NEW_NEW_APPOINMENT,envelope);

            result = (SoapObject) envelope.bodyIn;

            return result;


        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        return null;
    }

    // //insert new Appoinment Sending Massege
    private final String SOAP_NEW_APPOINMENT_SENDING_MASSAGE="http://tempuri.org/Appointmentmsg";
    private final String METHOD_NEW_APPOINMENT_SENDING_MASSAGE="Appointmentmsg";

    //insert new Homevisit Sending Massege
    public SoapObject NEW_APPOINMENT_SENDING_MASSAGE(String contactnew,String PatientContact,String labname,String ShourtUrl,
                                                     String labcontact) {

        SoapObject result=null;

        SoapObject request=new SoapObject(NAMESPACE,METHOD_NEW_APPOINMENT_SENDING_MASSAGE);

        request.addProperty("contactn",contactnew);
        request.addProperty("patient_contact",PatientContact);
        request.addProperty("LabName",labname);
        request.addProperty("shortUrl",ShourtUrl);
        request.addProperty("labContact",labcontact);

        SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        envelope.dotNet=true;

        HttpTransportSE httpTransportSE=new HttpTransportSE(URL);

        try {
            httpTransportSE.call(SOAP_NEW_APPOINMENT_SENDING_MASSAGE,envelope);

            result = (SoapObject) envelope.bodyIn;

            return result;


        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Password
    private final String SOAP_PASSWORD="http://tempuri.org/Password";
    private final String METHOD_PASSWORD="Password";

    // Password
    public SoapObject PASSWORD(String username,String mobileno,String applname,String applcode,
                                                     String emailis) {

        SoapObject result=null;

        SoapObject request=new SoapObject(NAMESPACE,METHOD_PASSWORD);

        request.addProperty("StrUsername",username);
        request.addProperty("StrMobileNo",mobileno);
        request.addProperty("StrApplName",applname);
        request.addProperty("StrApplCode",applcode);
        request.addProperty("StrEmailId",emailis);

        SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        envelope.dotNet=true;

        HttpTransportSE httpTransportSE=new HttpTransportSE(URL);

        try {
            httpTransportSE.call(SOAP_PASSWORD,envelope);

            result = (SoapObject) envelope.bodyIn;

            return result;


        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        return null;
    }

    // checkOtp
    private final String SOAP_CHECK_OTP="http://tempuri.org/CHKOTP";
    private final String METHOD_CHECK_OTP="CHKOTP";

    // checkOtp
    public SoapObject CHECK_OTP(String contact_no,String password) {

        SoapObject result=null;

        SoapObject request=new SoapObject(NAMESPACE,METHOD_CHECK_OTP);

        request.addProperty("contact_no",contact_no);
        request.addProperty("Password",password);

        SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        envelope.dotNet=true;

        HttpTransportSE httpTransportSE=new HttpTransportSE(URL);

        try {
            httpTransportSE.call(SOAP_CHECK_OTP,envelope);

            result = (SoapObject) envelope.bodyIn;

            return result;


        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        return null;
    }
    // update pass
    private final String SOAP_UpdatePassword="http://tempuri.org/UpdatePassword";
    private final String METHOD_UpdatePassword="UpdatePassword";

    // update pass
    public SoapObject UpdatePassword(String contact_no,String password) {

        SoapObject result=null;

        SoapObject request=new SoapObject(NAMESPACE,METHOD_UpdatePassword);

        request.addProperty("Contact_no",contact_no);
        request.addProperty("password",password);

        SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        envelope.dotNet=true;

        HttpTransportSE httpTransportSE=new HttpTransportSE(URL);

        try {
            httpTransportSE.call(SOAP_UpdatePassword,envelope);

            result = (SoapObject) envelope.bodyIn;

            return result;


        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        return null;
    }

    // FORGET PASSWORD
    private final String SOAP_ForgetPassword="http://tempuri.org/ForgetPassword";
    private final String METHOD_ForgetPassword="ForgetPassword";

    // FORGET PASSWORD
    public SoapObject FORGETPASSWROD(String username,String mobileno,String applname,String applcode,
                               String emailis) {

        SoapObject result=null;

        SoapObject request=new SoapObject(NAMESPACE,METHOD_ForgetPassword);

        request.addProperty("StrUsername",username);
        request.addProperty("StrMobileNo",mobileno);
        request.addProperty("StrApplName",applname);
        request.addProperty("StrApplCode",applcode);
        request.addProperty("StrEmailId",emailis);

        SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        envelope.dotNet=true;

        HttpTransportSE httpTransportSE=new HttpTransportSE(URL);

        try {
            httpTransportSE.call(SOAP_ForgetPassword,envelope);

            result = (SoapObject) envelope.bodyIn;

            return result;


        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        return null;
    }
    // update profile
    private final String SOAP_patientupdateprofile="http://tempuri.org/patientupdateprofile";
    private final String METHOD_patientupdateprofile="patientupdateprofile";

    // update profile
    public SoapObject patientupdateprofile(String name,String Patientcontact,String email,String dob,
                                     String gender,String City,String Area,String Pincode,String state,String Address,
                                      String contact) {

        SoapObject result=null;

        SoapObject request=new SoapObject(NAMESPACE,METHOD_patientupdateprofile);

        request.addProperty("Patient_name",name);
        request.addProperty("Contact_no",Patientcontact);
        request.addProperty("Email",email);
        request.addProperty("DOB",dob);
        request.addProperty("Gender",gender);
        request.addProperty("City",City);
        request.addProperty("Area",Area);
        request.addProperty("Pincode",Pincode);
        request.addProperty("State",state);
        request.addProperty("Address",Address);
        request.addProperty("contact",contact);

        SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        envelope.dotNet=true;

        HttpTransportSE httpTransportSE=new HttpTransportSE(URL);

        try {
            httpTransportSE.call(SOAP_patientupdateprofile,envelope);

            result = (SoapObject) envelope.bodyIn;

            return result;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        return null;
    }

    // check old password
    private final String SOAP_patient_check_old_password="http://tempuri.org/patient_check_old_password";
    private final String METHOD_patient_check_old_password="patient_check_old_password";

    // check old password
    public SoapObject patient_check_old_password(String contact,String password) {

        SoapObject result=null;

        SoapObject request=new SoapObject(NAMESPACE,METHOD_patient_check_old_password);

        request.addProperty("contact_no",contact);
        request.addProperty("password",password);

        SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        envelope.dotNet=true;

        HttpTransportSE httpTransportSE=new HttpTransportSE(URL);

        try {
            httpTransportSE.call(SOAP_patient_check_old_password,envelope);

            result = (SoapObject) envelope.bodyIn;

            return result;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Setting new password
    private final String SOAP_new_Password_patient="http://tempuri.org/new_Password_patient";
    private final String METHOD_new_Password_patient="new_Password_patient";

    // Setting new password
    public SoapObject new_Password_patient(String contact,String password) {

        SoapObject result=null;

        SoapObject request=new SoapObject(NAMESPACE,METHOD_new_Password_patient);

        request.addProperty("contact_no",contact);
        request.addProperty("password",password);

        SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        envelope.dotNet=true;

        HttpTransportSE httpTransportSE=new HttpTransportSE(URL);

        try {
            httpTransportSE.call(SOAP_new_Password_patient,envelope);

            result = (SoapObject) envelope.bodyIn;

            return result;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Report Test Wise
    private final String SOAP_Get_PatientTest="http://tempuri.org/Get_PatientTest";
    private final String METHOD_Get_PatientTest="Get_PatientTest";

    // Report Test Wise
    public SoapObject Get_PatientTest(String contact,String email) {

        SoapObject result=null;

        SoapObject request=new SoapObject(NAMESPACE,METHOD_Get_PatientTest);

        request.addProperty("contact_no",contact);
        request.addProperty("email",email);

        SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        envelope.dotNet=true;

        HttpTransportSE httpTransportSE=new HttpTransportSE(URL);

        try {
            httpTransportSE.call(SOAP_Get_PatientTest,envelope);

            result = (SoapObject) envelope.bodyIn;

            return result;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Report Date Wise
    private final String SOAP_Get_PatientDate="http://tempuri.org/Get_PatientDate";
    private final String METHOD_Get_PatientDate="Get_PatientDate";

    // Report Date Wise
    public SoapObject Get_PatientDate(String contact,String email) {

        SoapObject result=null;

        SoapObject request=new SoapObject(NAMESPACE,METHOD_Get_PatientDate);

        request.addProperty("contact_no",contact);
        request.addProperty("email",email);

        SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        envelope.dotNet=true;

        HttpTransportSE httpTransportSE=new HttpTransportSE(URL);

        try {
            httpTransportSE.call(SOAP_Get_PatientDate,envelope);

            result = (SoapObject) envelope.bodyIn;

            return result;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Report Test Wise
    private final String SOAP_Bind_PatientTestWise="http://tempuri.org/Bind_PatientTestWise";
    private final String METHOD_Bind_PatientTestWise="Bind_PatientTestWise";

    // Report Test Wise
    public SoapObject Bind_PatientTestWise(String contact,String email,String testId) {

        SoapObject result=null;

        SoapObject request=new SoapObject(NAMESPACE,METHOD_Bind_PatientTestWise);

        request.addProperty("contact_no",contact);
        request.addProperty("Email",email);
        request.addProperty("testId",testId);

        SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        envelope.dotNet=true;

        HttpTransportSE httpTransportSE=new HttpTransportSE(URL);

        try {
            httpTransportSE.call(SOAP_Bind_PatientTestWise,envelope);

            result = (SoapObject) envelope.bodyIn;

            return result;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Test Details According to Date and TEst Id
    private final String SOAP_Bind_PatientTestWiseDate="http://tempuri.org/Bind_PatientTestWiseDate";
    private final String METHOD_Bind_PatientTestWiseDate="Bind_PatientTestWiseDate";

    // Test Details According to Date and TEst Id
    public SoapObject Bind_PatientTestWiseDate(String contact,String email,String testId,String reportDate) {

        SoapObject result=null;

        SoapObject request=new SoapObject(NAMESPACE,METHOD_Bind_PatientTestWiseDate);

        request.addProperty("contact_no",contact);
        request.addProperty("EmailId",email);
        request.addProperty("testId",testId);
        request.addProperty("reportDate",reportDate);


        SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        envelope.dotNet=true;

        HttpTransportSE httpTransportSE=new HttpTransportSE(URL);

        try {
            httpTransportSE.call(SOAP_Bind_PatientTestWiseDate,envelope);

            result = (SoapObject) envelope.bodyIn;

            return result;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Test Details click on Date
    private final String SOAP_Get_PatientTest_date="http://tempuri.org/Get_PatientTest_date";
    private final String METHOD_Get_PatientTest_datee="Get_PatientTest_date";

    // Test Details click on Date
    public SoapObject Get_PatientTest_date(String contact,String email,String date) {

        SoapObject result=null;

        SoapObject request=new SoapObject(NAMESPACE,METHOD_Get_PatientTest_datee);

        request.addProperty("contact_no",contact);
        request.addProperty("email",email);
        request.addProperty("reportDate",date);

        SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        envelope.dotNet=true;

        HttpTransportSE httpTransportSE=new HttpTransportSE(URL);

        try {
            httpTransportSE.call(SOAP_Get_PatientTest_date,envelope);

            result = (SoapObject) envelope.bodyIn;

            return result;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        return null;
    }

    // getting the reason
    private final String SOAP_reasoncancel="http://tempuri.org/reasoncancel";
    private final String METHOD_reasoncancel="reasoncancel";

    // getting the reason
    public SoapObject reasoncancel() {

        SoapObject result=null;

        SoapObject request=new SoapObject(NAMESPACE,METHOD_reasoncancel);

        SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        envelope.dotNet=true;

        HttpTransportSE httpTransportSE=new HttpTransportSE(URL);

        try {
            httpTransportSE.call(SOAP_reasoncancel,envelope);

            result = (SoapObject) envelope.bodyIn;

            return result;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Cancel Home Visit
    private final String SOAP_Cancle_HomeVisit_P="http://tempuri.org/Cancle_HomeVisit_P";
    private final String METHOD_Cancle_HomeVisit_P="Cancle_HomeVisit_P";

    //cancel Home Visit
    public SoapObject Cancle_HomeVisit_P(String id,String reason) {

        SoapObject result=null;

        SoapObject request=new SoapObject(NAMESPACE,METHOD_Cancle_HomeVisit_P);

        request.addProperty("ID",id);
        request.addProperty("Reason",reason);

        SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        envelope.dotNet=true;

        HttpTransportSE httpTransportSE=new HttpTransportSE(URL);

        try {
            httpTransportSE.call(SOAP_Cancle_HomeVisit_P,envelope);

            result = (SoapObject) envelope.bodyIn;

            return result;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Cancel Home Visit
    private final String SOAP_Homevisitcancelmsg="http://tempuri.org/Homevisitcancelmsg";
    private final String METHOD_Homevisitcancelmsg="Homevisitcancelmsg";

    //cancel Home Visit
    public SoapObject Homevisitcancelmsg(String AppoinmentId,String contactn,String PAtientName,String Visitdate,
                                         String VisitTime,String LabName,String LabContact) {

        SoapObject result=null;

        SoapObject request=new SoapObject(NAMESPACE,METHOD_Homevisitcancelmsg);

        request.addProperty("AppointID",AppoinmentId);
        request.addProperty("contactn",contactn);
        request.addProperty("PAtientName",PAtientName);
        request.addProperty("Visitdate",Visitdate);
        request.addProperty("VisitTime",VisitTime);
        request.addProperty("LabName",LabName);
        request.addProperty("LabContact",LabContact);

        SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        envelope.dotNet=true;

        HttpTransportSE httpTransportSE=new HttpTransportSE(URL);

        try {
            httpTransportSE.call(SOAP_Homevisitcancelmsg,envelope);

            result = (SoapObject) envelope.bodyIn;

            return result;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        return null;
    }
}
