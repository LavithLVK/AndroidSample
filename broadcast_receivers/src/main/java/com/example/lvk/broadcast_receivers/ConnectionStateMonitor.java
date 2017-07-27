package com.example.lvk.broadcast_receivers;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.ConnectivityManager.NetworkCallback;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;

public class ConnectionStateMonitor extends NetworkCallback {

    final NetworkRequest networkRequest;

    public ConnectionStateMonitor() {
        networkRequest = new NetworkRequest.Builder().addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR).addTransportType(NetworkCapabilities.TRANSPORT_WIFI).build();
    }

    public void enable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        connectivityManager.registerNetworkCallback(networkRequest , this);
    }

    // Likewise, you can have a disable method that simply calls ConnectivityManager#unregisterCallback(networkRequest) too.

    @Override
    public void onAvailable(Network network) {
        super.onAvailable(network);
        ConnectivityReceiver.listener.onNetworkConnectionChange(true);
        // Do what you need to do here
    }

    @Override
    public void onLost(Network network) {
        super.onLost(network);
        ConnectivityReceiver.listener.onNetworkConnectionChange(false);
    }
}