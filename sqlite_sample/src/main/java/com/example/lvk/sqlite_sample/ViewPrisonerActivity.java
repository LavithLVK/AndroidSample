package com.example.lvk.sqlite_sample;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.lvk.sqlite_sample.prisoner_recylcer.PrisonerListFragment;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class ViewPrisonerActivity extends AppCompatActivity {

    FragmentManager fm;
    FragmentTransaction ft;
    PrisonDBHelper prisonDBHelper;
    List<Prisoner> prisoners;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_prisoner);
        prisonDBHelper=new PrisonDBHelper(getApplicationContext(),4);
        prisoners = prisonDBHelper.getAllPrisoners();
        fm=getFragmentManager();
        ft=fm.beginTransaction();
        Gson gson=new Gson();
        ArrayList<String> json_prisoners =new ArrayList<String>();
        for(int i=0;i<prisoners.size();i++)
        {
            json_prisoners.add(gson.toJson(prisoners.get(i)));
        }
        Bundle args=new Bundle();
        args.putStringArrayList("prisoners",json_prisoners);
        PrisonerListFragment pl=new PrisonerListFragment();
        pl.setContext(this);
        pl.setArguments(args);
        ft.add(R.id.list_container,pl,"list");
        ft.addToBackStack(null);
        ft.commit();
    }

    public void openPrisonerContent(Prisoner prisoner){
        FragmentTransaction ft=fm.beginTransaction();
        PrisonerContentFragment pc=new PrisonerContentFragment();
        Bundle args=new Bundle();
        Gson gson=new Gson();
        args.putString("prisoner",gson.toJson(prisoner));
        pc.setArguments(args);
        if(fm.getBackStackEntryCount()>=1){
            ft.replace(R.id.view_container,pc);
        }
        else
        {
            ft.add(R.id.view_container,pc);
        }
        ft.addToBackStack(null);
        ft.commit();
    }

}
