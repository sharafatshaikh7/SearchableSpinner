package com.mylabpatient.DataSource;

/**
 * Created by Sunil on 3/2/2017.
 */

public class ReportTestWiseDataSource {

    String testId;
    String testname;

    public ReportTestWiseDataSource(String testId,String testname){
        this.testId=testId;
        this.testname=testname;
    }

    public String getTestId() {
        return testId;
    }

    public void setTestId(String testId) {
        this.testId = testId;
    }

    public String getTestname() {
        return testname;
    }

    public void setTestname(String testname) {
        this.testname = testname;
    }


}
