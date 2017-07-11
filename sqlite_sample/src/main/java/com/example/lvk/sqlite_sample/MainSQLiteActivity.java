package com.example.lvk.sqlite_sample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class MainSQLiteActivity extends AppCompatActivity {

    Button addPrisoner;
    Button viewPrisoners;
    List<Prisoner> prisoners;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sqlite);
        addPrisoner =(Button)findViewById(R.id.add_prisoner);
        viewPrisoners =(Button)findViewById(R.id.view_prisoner);
        viewPrisoners.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),ViewPrisonerActivity.class);
                startActivity(intent);
            }
        });

        addPrisoner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),AddPrisonerActivity.class);
                startActivity(intent);
            }
        });
    }

}
