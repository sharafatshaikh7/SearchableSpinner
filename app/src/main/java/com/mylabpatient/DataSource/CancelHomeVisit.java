package com.mylabpatient.DataSource;

/**
 * Created by Sunil on 2/25/2017.
 */

public class CancelHomeVisit {

    //String Status1;
    String Id;
    String patient_id;
    String Patient_Name;
    String Date;
    String Time;
    String Contact;
    String Area;
    String Address;
    String Prefered_Lab;
    String Remark;
    String File_Name;
    String File_Path;
    String Booked_Flag;
    String Current_Date;
    String Cancel;
    String Status;
    String Patient_Contact;
    String Patient_Email;
    String Lab_Code;
    String Homevisit_Id;
    String Row_Sent;


    public CancelHomeVisit(String Id, String patient_id, String Patient_Name, String Date, String Time,
                           String Contact, String Area, String Address, String Prefered_Lab, String Remark, String File_Name,
                           String File_Path, String Booked_Flag, String Current_Date, String Cancel, String Status,
                           String Patient_Contact,String Patient_Email, String Lab_Code, String Homevisit_Id, String Row_Sent){

        this.Id=Id;
        this.patient_id=patient_id;
        this.Patient_Name=Patient_Name;
        this.Date=Date;
        this.Time=Time;
        this.Contact=Contact;
        this.Area=Area;
        this.Address=Address;
        this.Prefered_Lab=Prefered_Lab;
        this.Remark=Remark;
        this.File_Name=File_Name;
        this.File_Path=File_Path;
        this.Booked_Flag=Booked_Flag;
        this.Current_Date=Current_Date;
        this.Cancel=Cancel;
        this.Status=Status;
        this.Patient_Contact=Patient_Contact;
        this.Patient_Email=Patient_Email;
        this.Lab_Code=Lab_Code;
        this.Homevisit_Id=Homevisit_Id;
        this.Row_Sent=Row_Sent;
    }

    public String getPrefered_Lab() {
        return Prefered_Lab;
    }

    public void setPrefered_Lab(String prefered_Lab) {
        Prefered_Lab = prefered_Lab;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(String patient_id) {
        this.patient_id = patient_id;
    }

    public String getPatient_Name() {
        return Patient_Name;
    }

    public void setPatient_Name(String patient_Name) {
        Patient_Name = patient_Name;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }

    public String getArea() {
        return Area;
    }

    public void setArea(String area) {
        Area = area;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    public String getFile_Name() {
        return File_Name;
    }

    public void setFile_Name(String file_Name) {
        File_Name = file_Name;
    }

    public String getFile_Path() {
        return File_Path;
    }

    public void setFile_Path(String file_Path) {
        File_Path = file_Path;
    }

    public String getBooked_Flag() {
        return Booked_Flag;
    }

    public void setBooked_Flag(String booked_Flag) {
        Booked_Flag = booked_Flag;
    }

    public String getCurrent_Date() {
        return Current_Date;
    }

    public void setCurrent_Date(String current_Date) {
        Current_Date = current_Date;
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

    public String getPatient_Contact() {
        return Patient_Contact;
    }

    public void setPatient_Contact(String patient_Contact) {
        Patient_Contact = patient_Contact;
    }

    public String getPatient_Email() {
        return Patient_Email;
    }

    public void setPatient_Email(String patient_Email) {
        Patient_Email = patient_Email;
    }

    public String getLab_Code() {
        return Lab_Code;
    }

    public void setLab_Code(String lab_Code) {
        Lab_Code = lab_Code;
    }

    public String getHomevisit_Id() {
        return Homevisit_Id;
    }

    public void setHomevisit_Id(String homevisit_Id) {
        Homevisit_Id = homevisit_Id;
    }

    public String getRow_Sent() {
        return Row_Sent;
    }

    public void setRow_Sent(String row_Sent) {
        Row_Sent = row_Sent;
    }


}
