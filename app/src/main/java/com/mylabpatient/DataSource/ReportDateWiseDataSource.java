package com.mylabpatient.DataSource;

/**
 * Created by Sunil on 3/2/2017.
 */

public class ReportDateWiseDataSource {

    String Date;

    public ReportDateWiseDataSource(String Date){
        this.Date=Date;
    }
    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}
