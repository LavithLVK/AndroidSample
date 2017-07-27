package com.example.lvk.broadcast_receivers;

import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainBroadcastActivity extends AppCompatActivity implements ConnectivityReceiver.OnConnectivityChangeListener{

    Button btnCheck;
    FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_broadcast);
        btnCheck=(Button)findViewById(R.id.check_connection);
        fab=(FloatingActionButton)findViewById(R.id.fab);
        ConnectionStateMonitor connectionStateMonitor=new ConnectionStateMonitor();
        connectionStateMonitor.enable(this);

        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkConnection(view);
            }
        });
    }

    private void checkConnection(View view){
        boolean isConnected=ConnectivityReceiver.isConnected();
        showSnack(isConnected);
    }

    @Override
    public void onNetworkConnectionChange(Boolean isConnected) {
        showSnack(isConnected);
    }

    public void showSnack(Boolean isConnected){
        String message;
        int color;
        message=isConnected?":) :) We are connected!!!!!!!!!":":( :( No internet!!!";
        color=isConnected? Color.GREEN:Color.RED;
        Snackbar snackbar= Snackbar.make(findViewById(R.id.main_activity),message,Snackbar.LENGTH_LONG);

        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(color);
        snackbar.show();
    }

    @Override
    protected void onResume() {
        super.onResume();

        MyApplication.getInstance().setOnConnectionChangeListener(this);
    }
}
