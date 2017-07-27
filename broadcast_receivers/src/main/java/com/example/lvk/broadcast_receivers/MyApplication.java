package com.example.lvk.broadcast_receivers;

import android.app.Application;
import android.content.Context;

/**
 * Created by lavith.edam on 7/26/2017.
 */

public class MyApplication extends Application {

    private static MyApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance=this;
    }

    public static MyApplication getInstance(){
        return mInstance;
    }

    public void setOnConnectionChangeListener(ConnectivityReceiver.OnConnectivityChangeListener listnener){
        ConnectivityReceiver.listener=listnener;
        ConnectivityReceiver.ctx=(Context) listnener;
    }

}
