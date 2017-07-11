package com.example.lvk.fragments1;

import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.FragmentManager;

public class MainActivity extends AppCompatActivity {
 private static int count=0;
    FragmentManager fm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fm = getFragmentManager();
    }

    public void addFragment(String data){
        FragmentTransaction ft=fm.beginTransaction();
        ContentFragment frag=new ContentFragment();
        Bundle args=new Bundle();
        args.putString("data",data);
        frag.setArguments(args);
        if(fm.getBackStackEntryCount()>=1){
            ft.replace(R.id.container,frag,"frag"+count++);
        }
        else{
        ft.add(R.id.container,frag,"frag"+count++);
        }
        ft.addToBackStack("frag"+(count-1));
        ft.commit();
    }
}
