package com.example.lvk.sqlite_sample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.lvk.sqlite_sample.filter.MinMaxFilter;

public class AddPrisonerActivity extends AppCompatActivity {
    PrisonDBHelper prisonDBHelper;
    TextView name;
    TextView age;
    Spinner crime;
    Spinner sentenced;
    Button add;
    Button cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_prisoner);
        Intent intent=getIntent();

        name=(TextView)findViewById(R.id.prisoner_name);
        age=(TextView)findViewById(R.id.prisoner_age);
        age.setFilters(new InputFilter[]{new MinMaxFilter(1,127)});
        crime=(Spinner)findViewById(R.id.prisoner_crime);
        crime.setPrompt("Crime");
        sentenced=(Spinner)findViewById(R.id.prisoner_status);
        add=(Button)findViewById(R.id.add_record);
        cancel =(Button)findViewById(R.id.cancel_action);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.getText().toString().length()!=0 && age.getText().toString().length()!=0){
                    prisonDBHelper=new PrisonDBHelper(getApplicationContext(),4);
                    Prisoner prisoner=new Prisoner();
                    prisoner.setName(name.getText().toString());
                    prisoner.setAge(Integer.parseInt(age.getText().toString()));
                    prisoner.setCrime(crime.getSelectedItem().toString());
                    prisoner.setSentenced(Boolean.parseBoolean(sentenced.getSelectedItem().toString()));
                    prisonDBHelper.insertPrisoner(prisoner);
                    finish();
                }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
