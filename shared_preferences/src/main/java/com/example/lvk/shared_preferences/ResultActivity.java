package com.example.lvk.shared_preferences;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        TextView name=(TextView)findViewById(R.id.name_text);
        TextView age=(TextView)findViewById(R.id.age_text);
        TextView status=(TextView)findViewById(R.id.status_text);

        SharedPreferences sharedPreferences=getSharedPreferences("LvkPrefs",getApplicationContext().MODE_PRIVATE);
        Intent intent = getIntent();
        String modelString = intent.getStringExtra("DataModel");//sharedPreferences.getString("DataModel", "");
        Gson son=new Gson();
        DataModel model= son.fromJson(modelString,DataModel.class);
        name.setText(model.getName());
        age.setText(model.getAge());
        status.setText(model.getStatus());
    }

}
