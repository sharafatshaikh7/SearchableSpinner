package com.mylabpatient.DataSource;

/**
 * Created by Sunil on 2/27/2017.
 */

public class PreviousAppoinmentDataSource {

    String Status1;
    String Id;
    String username_book;
    String patient_id;
    String Date;
    String Time;
    String Contact;
    String Area;
    String Address;
    String Prefered_Lab;
    String Remark;
    String Flag;
    String File_Name;
    String File_Path;
    String Cancel;
    String Status;
    String Patient_Contact;
    String Patient_Email;
    String AppoinmentId;
    String Age;
    String Lab_Code;
    String Row_Sent;
    String QRCode;

    public PreviousAppoinmentDataSource(String Status1, String Id, String username_book, String patient_id, String Date, String Time,
                                        String Contact, String Area, String Address, String Prefered_Lab, String Remark, String Flag, String File_Name,
                                        String File_Path, String Cancel, String Status, String Patient_Contact, String Patient_Email, String AppoinmentId,
                                        String Age, String Lab_Code, String Row_Sent, String QRCode){


        this.Status1=Status1;
        this.Id=Id;
        this.username_book=username_book;
        this.patient_id=patient_id;
        this.Date=Date;
        this.Time=Time;
        this.Contact=Contact;
        this.Area=Area;
        this.Address=Address;
        this.Prefered_Lab=Prefered_Lab;
        this.Remark=Remark;
        this.Flag=Flag;
        this.File_Name=File_Name;
        this.File_Path=File_Path;
        this.Cancel=Cancel;
        this.Status=Status;
        this.Patient_Contact=Patient_Contact;
        this.Patient_Email=Patient_Email;
        this.Lab_Code=Lab_Code;
        this.AppoinmentId=AppoinmentId;
        this.Age=Age;
        this.QRCode=QRCode;
        this.Row_Sent=Row_Sent;

    }



    public String getArea() {
        return Area;
    }

    public void setArea(String area) {
        Area = area;
    }

    public String getStatus1() {
        return Status1;
    }

    public void setStatus1(String status1) {
        Status1 = status1;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getUsername_book() {
        return username_book;
    }

    public void setUsername_book(String username_book) {
        this.username_book = username_book;
    }

    public String getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(String patient_id) {
        this.patient_id = patient_id;
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

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPrefered_Lab() {
        return Prefered_Lab;
    }

    public void setPrefered_Lab(String prefered_Lab) {
        Prefered_Lab = prefered_Lab;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    public String getFlag() {
        return Flag;
    }

    public void setFlag(String flag) {
        Flag = flag;
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

    public String getAppoinmentId() {
        return AppoinmentId;
    }

    public void setAppoinmentId(String appoinmentId) {
        AppoinmentId = appoinmentId;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getLab_Code() {
        return Lab_Code;
    }

    public void setLab_Code(String lab_Code) {
        Lab_Code = lab_Code;
    }

    public String getRow_Sent() {
        return Row_Sent;
    }

    public void setRow_Sent(String row_Sent) {
        Row_Sent = row_Sent;
    }

    public String getQRCode() {
        return QRCode;
    }

    public void setQRCode(String QRCode) {
        this.QRCode = QRCode;
    }

}
