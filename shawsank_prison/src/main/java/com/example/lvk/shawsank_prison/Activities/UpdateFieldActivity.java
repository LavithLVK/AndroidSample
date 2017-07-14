package com.example.lvk.shawsank_prison.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.lvk.shawsank_prison.R;

public class UpdateFieldActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_field);
        Intent intent=getIntent();
        enableField(intent.getStringExtra("EnableField"));

    }

    private void enableField(String field){
        switch(field)
        {
            case "Name":
                break;
            case "Email":
                break;
            case "Mobile":
                break;
            case "DOB":
                break;
            case "Crime":
                break;
            case "image":
                break;
            case "sentence":
                break;
            default:

        }


    }
}
