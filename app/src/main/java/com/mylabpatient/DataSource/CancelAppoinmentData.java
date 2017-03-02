package com.mylabpatient.DataSource;

/**
 * Created by Sunil on 2/28/2017.
 */

public class CancelAppoinmentData {

    String ID;
    String PATIENT_ID;
    String Patient_name;
    String DATE;
    String TIME;
    String CONTACT;
    String AREA;
    String ADDRESS;
    String PREFERED_LAB;
    String REMARK;
    String BOOKED_FLAG;
    String FILENAME;
    String FILEPATH;
    String Cancel;
    String Status;
    String Booked_By;
    String PatientContact;
    String PatientEmail;
    String AppoinmentId;
    String age;
    String LabCode;
    String CancelReason;
    String RowSent;

    public CancelAppoinmentData( String ID, String PATIENT_ID, String Patient_name, String DATE, String TIME, String CONTACT,
            String AREA, String ADDRESS, String PREFERED_LAB, String REMARK, String BOOKED_FLAG, String FILENAME, String FILEPATH,
            String Cancel, String Status, String Booked_By, String PatientContact, String PatientEmail, String AppoinmentId, String age,
            String LabCode, String CancelReason, String RowSent){

        this.ID=ID;
        this.PATIENT_ID=PATIENT_ID;
        this.Patient_name=Patient_name;
        this.DATE=DATE;
        this.TIME=TIME;
        this.CONTACT=CONTACT;
        this.AREA=AREA;
        this.ADDRESS=ADDRESS;
        this.PREFERED_LAB=PREFERED_LAB;
        this.REMARK=REMARK;
        this.BOOKED_FLAG=BOOKED_FLAG;
        this.FILENAME=FILENAME;
        this.FILEPATH=FILEPATH;
        this.Cancel=Cancel;
        this.Status=Status;
        this.Booked_By=Booked_By;
        this.PatientContact=PatientContact;
        this.PatientEmail=PatientEmail;
        this.AppoinmentId=AppoinmentId;
        this.age=age;
        this.LabCode=LabCode;
        this.CancelReason=CancelReason;
        this.RowSent=RowSent;
    }

    public String getBOOKED_FLAG() {
        return BOOKED_FLAG;
    }

    public void setBOOKED_FLAG(String BOOKED_FLAG) {
        this.BOOKED_FLAG = BOOKED_FLAG;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPATIENT_ID() {
        return PATIENT_ID;
    }

    public void setPATIENT_ID(String PATIENT_ID) {
        this.PATIENT_ID = PATIENT_ID;
    }

    public String getPatient_name() {
        return Patient_name;
    }

    public void setPatient_name(String patient_name) {
        Patient_name = patient_name;
    }

    public String getDATE() {
        return DATE;
    }

    public void setDATE(String DATE) {
        this.DATE = DATE;
    }

    public String getTIME() {
        return TIME;
    }

    public void setTIME(String TIME) {
        this.TIME = TIME;
    }

    public String getCONTACT() {
        return CONTACT;
    }

    public void setCONTACT(String CONTACT) {
        this.CONTACT = CONTACT;
    }

    public String getAREA() {
        return AREA;
    }

    public void setAREA(String AREA) {
        this.AREA = AREA;
    }

    public String getADDRESS() {
        return ADDRESS;
    }

    public void setADDRESS(String ADDRESS) {
        this.ADDRESS = ADDRESS;
    }

    public String getPREFERED_LAB() {
        return PREFERED_LAB;
    }

    public void setPREFERED_LAB(String PREFERED_LAB) {
        this.PREFERED_LAB = PREFERED_LAB;
    }

    public String getREMARK() {
        return REMARK;
    }

    public void setREMARK(String REMARK) {
        this.REMARK = REMARK;
    }

    public String getFILENAME() {
        return FILENAME;
    }

    public void setFILENAME(String FILENAME) {
        this.FILENAME = FILENAME;
    }

    public String getFILEPATH() {
        return FILEPATH;
    }

    public void setFILEPATH(String FILEPATH) {
        this.FILEPATH = FILEPATH;
    }

    public String getCancel() {
        return Cancel;
    }

    public void setCancel(String cancel) {
        Cancel = cancel;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getBooked_By() {
        return Booked_By;
    }

    public void setBooked_By(String booked_By) {
        Booked_By = booked_By;
    }

    public String getPatientContact() {
        return PatientContact;
    }

    public void setPatientContact(String patientContact) {
        PatientContact = patientContact;
    }

    public String getPatientEmail() {
        return PatientEmail;
    }

    public void setPatientEmail(String patientEmail) {
        PatientEmail = patientEmail;
    }

    public String getAppoinmentId() {
        return AppoinmentId;
    }

    public void setAppoinmentId(String appoinmentId) {
        AppoinmentId = appoinmentId;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getLabCode() {
        return LabCode;
    }

    public void setLabCode(String labCode) {
        LabCode = labCode;
    }

    public String getCancelReason() {
        return CancelReason;
    }

    public void setCancelReason(String cancelReason) {
        CancelReason = cancelReason;
    }

    public String getRowSent() {
        return RowSent;
    }

    public void setRowSent(String rowSent) {
        RowSent = rowSent;
    }



}
