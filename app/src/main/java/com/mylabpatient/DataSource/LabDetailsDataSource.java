package com.mylabpatient.DataSource;

/**
 * Created by Sunil on 2/28/2017.
 */

public class LabDetailsDataSource {

    String Labname;
    String id;
    String Labcode;
    String Labname1;
    String LabAddress;
    String LabEmail;
    String LabContact;
    String LabWebservicesUrl;

    public LabDetailsDataSource(String Labname, String id, String Labcode, String Labname1, String LabAddress, String LabEmail,
                                  String LabContact,String LabWebservicesUrl){

        this.Labname=Labname;
        this.id=id;
        this.Labcode=Labcode;
        this.Labname1=Labname1;
        this.LabAddress=LabAddress;
        this.LabEmail=LabEmail;
        this.LabContact=LabContact;
        this.LabWebservicesUrl=LabWebservicesUrl;
    }

    public String getLabContact() {
        return LabContact;
    }

    public void setLabContact(String labContact) {
        LabContact = labContact;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabcode() {
        return Labcode;
    }

    public void setLabcode(String labcode) {
        Labcode = labcode;
    }

    public String getLabname() {
        return Labname;
    }

    public void setLabname(String labname) {
        Labname = labname;
    }

    public String getLabAddress() {
        return LabAddress;
    }

    public void setLabAddress(String labAddress) {
        LabAddress = labAddress;
    }

    public String getLabEmail() {
        return LabEmail;
    }

    public void setLabEmail(String labEmail) {
        LabEmail = labEmail;
    }

    public String getLabWebservicesUrl() {
        return LabWebservicesUrl;
    }

    public void setLabWebservicesUrl(String labWebservicesUrl) {
        LabWebservicesUrl = labWebservicesUrl;
    }
}
