package com.example.lvk.shared_preferences;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.gson.Gson;

import static com.example.lvk.shared_preferences.R.string.status_prompt;

public class MainSharedActivity extends AppCompatActivity {

    public static String LvkPrefs="LvkPrefs";
    public static String name="LvkPrefs";

    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_shared);

        final EditText edtxt1 =(EditText)findViewById(R.id.name);
        final EditText edtxt2 =(EditText)findViewById(R.id.age);
        final Spinner edtxt3 =(Spinner) findViewById(R.id.status);
        Button b1=(Button)findViewById(R.id.button);
        sharedPreferences=getSharedPreferences(LvkPrefs, getApplicationContext().MODE_PRIVATE);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                DataModel model =new DataModel();
                model.setName(edtxt1.getText().toString());
                model.setAge(edtxt2.getText().toString());
                model.setStatus(edtxt3.getSelectedItem().toString());
                SharedPreferences.Editor editor = sharedPreferences.edit();
                Gson gson=new Gson();
                String json = gson.toJson(model);
                editor.putString("DataModel", json);
                editor.commit();
                intent.putExtra("DataModel",json);
                startActivity(intent);
            }
        });
    }
}
