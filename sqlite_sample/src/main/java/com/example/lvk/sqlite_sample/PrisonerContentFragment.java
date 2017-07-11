package com.example.lvk.sqlite_sample;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;

/**
 * Created by lavith.edam on 6/22/2017.
 */

public class PrisonerContentFragment extends Fragment{

    private Prisoner prisoner;
    TextView name;
    TextView age;
    TextView crime;
    TextView isSentenced;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.prisoner_content,container,false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        name=(TextView)getView().findViewById(R.id.label_name);
        age=(TextView)getView().findViewById(R.id.label_age);
        crime=(TextView)getView().findViewById(R.id.label_crime);
        isSentenced =(TextView)getView().findViewById(R.id.label_sentenced);
        Bundle bundle=getArguments();
        String prisoner_json= bundle.get("prisoner").toString();
        Gson gson=new Gson();
        prisoner=gson.fromJson(prisoner_json,Prisoner.class);
        name.setText(prisoner.getName());
        age.setText(String.valueOf(prisoner.getAge()));
        crime.setText(prisoner.getCrime());
        isSentenced.setText(prisoner.getSentenced().toString());
    }
}
