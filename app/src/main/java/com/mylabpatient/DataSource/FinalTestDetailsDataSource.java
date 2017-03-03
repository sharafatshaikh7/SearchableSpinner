package com.mylabpatient.DataSource;

import android.Manifest;

/**
 * Created by Sunil on 3/3/2017.
 */

public class FinalTestDetailsDataSource {

    String TitleName;
    String FieldNo;
    String NormalValue;
    String MAxValue;
    String MinValue;
    String DocName;
    String CenterName;
    String FildValue;
    String TestUnit;


    public FinalTestDetailsDataSource(String TitleName, String FieldNo, String NormalValue, String MAxValue,
            String MinValue, String DocName, String CenterName, String FildValue, String TestUnit){

        this.TitleName=TitleName;
        this.FieldNo=FieldNo;
        this.NormalValue=NormalValue;
        this.MAxValue=MAxValue;
        this.MinValue=MinValue;
        this.DocName=DocName;
        this.CenterName=CenterName;
        this.FildValue=FildValue;
        this.TestUnit=TestUnit;
    }

    public String getMinValue() {
        return MinValue;
    }

    public void setMinValue(String minValue) {
        MinValue = minValue;
    }

    public String getTitleName() {
        return TitleName;
    }

    public void setTitleName(String titleName) {
        TitleName = titleName;
    }

    public String getFieldNo() {
        return FieldNo;
    }

    public void setFieldNo(String fieldNo) {
        FieldNo = fieldNo;
    }

    public String getNormalValue() {
        return NormalValue;
    }

    public void setNormalValue(String normalValue) {
        NormalValue = normalValue;
    }

    public String getMAxValue() {
        return MAxValue;
    }

    public void setMAxValue(String MAxValue) {
        this.MAxValue = MAxValue;
    }

    public String getDocName() {
        return DocName;
    }

    public void setDocName(String docName) {
        DocName = docName;
    }

    public String getCenterName() {
        return CenterName;
    }

    public void setCenterName(String centerName) {
        CenterName = centerName;
    }

    public String getFildValue() {
        return FildValue;
    }

    public void setFildValue(String fildValue) {
        FildValue = fildValue;
    }

    public String getTestUnit() {
        return TestUnit;
    }

    public void setTestUnit(String testUnit) {
        TestUnit = testUnit;
    }
}
