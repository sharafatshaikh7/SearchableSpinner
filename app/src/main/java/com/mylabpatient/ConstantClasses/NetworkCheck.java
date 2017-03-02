package com.mylabpatient.ConstantClasses;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Sunil on 1/25/2017.
 */

public class NetworkCheck {

    Context mCtx;

    public NetworkCheck(Context context)
    {
        mCtx=context;
    }

    public boolean isConnectinToInternet()
    {
        ConnectivityManager connectivityManager= (ConnectivityManager) mCtx.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager !=null)
        {
            NetworkInfo[] info =connectivityManager.getAllNetworkInfo();
            if(info != null)
            {
                for(NetworkInfo aninfo : info)
                {
                    if(aninfo.getState() == NetworkInfo.State.CONNECTED)
                    {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
