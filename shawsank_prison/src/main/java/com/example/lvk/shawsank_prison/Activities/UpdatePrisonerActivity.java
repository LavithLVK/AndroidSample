package com.example.lvk.shawsank_prison.Activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.lvk.shawsank_prison.R;
import com.example.lvk.shawsank_prison.Utils.Utility;

public class UpdatePrisonerActivity extends AppCompatActivity {

    Button btnName;
    Button btnEmail;
    Button btnMobile;
    Button btnImage;
    Button btnDob;
    Button btnCrime;
    Button btnSentence;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_prisoner);
        btnName=(Button)findViewById(R.id.update_button_name);
        btnEmail=(Button)findViewById(R.id.update_button_email);
        btnMobile=(Button)findViewById(R.id.update_button_mobile);
        btnCrime=(Button)findViewById(R.id.update_button_crime);
        btnDob=(Button)findViewById(R.id.update_button_dob);
        btnImage=(Button)findViewById(R.id.update_button_photo);
        btnSentence=(Button)findViewById(R.id.update_button_sentenced);
        btnName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fieldClick(v,getString(R.string.label_name));
            }
        });
        btnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fieldClick(v,getString(R.string.label_email));
            }
        });
        btnMobile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fieldClick(v,getString(R.string.label_mobile));
            }
        });
        btnCrime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fieldClick(v,getString(R.string.label_crime));
            }
        });
        btnDob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fieldClick(v,getString(R.string.label_dob));
            }
        });
        btnSentence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fieldClick(v,getString(R.string.label_sentenced));
            }
        });
        btnImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fieldClick(v,getString(R.string.label_image));
            }
        });
    }



    private void fieldClick(View view, String field){
        Intent intent = new Intent(getApplicationContext(),UpdateFieldActivity.class);
        switch(field){
            case "Name":
                intent.putExtra("EnableField","Name");
                break;
            case "Email id":
                intent.putExtra("EnableField","Email");
                break;
            case "Mobile no.":
                intent.putExtra("EnableField","Mobile");
                break;
            case "Crime":
                intent.putExtra("EnableField","Crime");
                break;
            case "Date Of Birth":
                intent.putExtra("EnableField","DOB");
                break;
            case "Is Sentenced":
                intent.putExtra("EnableField","sentence");
                break;
            case "Profile Image":
                intent.putExtra("EnableField","Image");
                break;
        }
      startActivity(intent);
    }


}
