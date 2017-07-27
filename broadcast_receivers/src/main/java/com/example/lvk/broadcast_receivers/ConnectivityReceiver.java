package com.example.lvk.broadcast_receivers;

import android.Manifest;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;

/**
 * Created by lavith.edam on 7/26/2017.
 */

public class ConnectivityReceiver extends BroadcastReceiver {

    public static Context ctx;

    public static OnConnectivityChangeListener listener;
    private static int ACCESS_NETWORK_STATE_CODE = 156;

    public ConnectivityReceiver(){
        super();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        ActivityCompat.requestPermissions((Activity) ctx, new String[]{Manifest.permission.ACCESS_NETWORK_STATE}, ACCESS_NETWORK_STATE_CODE);
        ConnectivityManager connectivityManager=(ConnectivityManager)MyApplication.getInstance().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info=connectivityManager.getActiveNetworkInfo();
        Boolean isConnected = info!=null && info.isConnectedOrConnecting();

        if(listener!=null){
            listener.onNetworkConnectionChange(isConnected);
        }
    }

    public static Boolean isConnected(){
        ActivityCompat.requestPermissions((Activity) ctx, new String[]{Manifest.permission.ACCESS_NETWORK_STATE}, ACCESS_NETWORK_STATE_CODE);
        ConnectivityManager connectivityManager=(ConnectivityManager)MyApplication.getInstance().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info=connectivityManager.getActiveNetworkInfo();
        return info!=null && info.isConnectedOrConnecting();
    }

    public interface OnConnectivityChangeListener{
        void onNetworkConnectionChange(Boolean isConnected);
    }
}
