package com.mylabpatient.DataSource;

/**
 * Created by Sunil on 3/3/2017.
 */

public class DateWiseTestDataSource {

    String testId;
    String testName;

    public DateWiseTestDataSource(String testId, String testName){
        this.testId=testId;
        this.testName=testName;
    }

    public String getTestId() {
        return testId;
    }

    public void setTestId(String testId) {
        this.testId = testId;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }




}
