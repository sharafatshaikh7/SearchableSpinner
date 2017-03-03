package com.mylabpatient.DataSource;

/**
 * Created by Sunil on 3/3/2017.
 */

public class CancelReasonDataSource {

    String Reason1;
    String Reason;
    String id;

    public CancelReasonDataSource(String Reason,String id,String Reason1){
        this.Reason=Reason;
        this.id=id;
        this.Reason1=Reason1;
    }

    public String getReason() {
        return Reason;
    }

    public void setReason(String reason) {
        Reason = reason;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReason1() {
        return Reason1;
    }

    public void setReason1(String reason1) {
        Reason1 = reason1;
    }

}
