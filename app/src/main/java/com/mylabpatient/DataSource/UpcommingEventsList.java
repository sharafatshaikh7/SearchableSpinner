package com.mylabpatient.DataSource;

/**
 * Created by Sunil on 2/28/2017.
 */

public class UpcommingEventsList {

    String FromDate;
    String ToDate;
    String Package;
    String TestName;
    String Price;

    public UpcommingEventsList( String FromDate, String ToDate, String Package, String TestName, String Price){

        this.FromDate=FromDate;
        this.ToDate=ToDate;
        this.Package=Package;
        this.TestName=TestName;
        this.Price=Price;
    }

    public String getPackage() {
        return Package;
    }

    public void setPackage(String aPackage) {
        Package = aPackage;
    }

    public String getFromDate() {
        return FromDate;
    }

    public void setFromDate(String fromDate) {
        FromDate = fromDate;
    }

    public String getToDate() {
        return ToDate;
    }

    public void setToDate(String toDate) {
        ToDate = toDate;
    }

    public String getTestName() {
        return TestName;
    }

    public void setTestName(String testName) {
        TestName = testName;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }
}
