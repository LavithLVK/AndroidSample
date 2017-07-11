package com.example.lvk.fragment_navigation;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.annotation.StringDef;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuView;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    FragmentManager fm;
    FragmentTransaction ft;
    LeftFragmentOne lf1;
    LeftFragmentTwo lf2;
    LeftFragmentThree lf3;
    RightFragmentOne rf1;
    RightFragmentTwo rf2;
    RightFragmentThree rf3;
    Button loadFrags;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fm=getFragmentManager();
        loadFrags=(Button)findViewById(R.id.button1);
        loadFrags.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragments();
            }
        });
        loadFragments();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(fm.getBackStackEntryCount()<1){
            loadFrags.setEnabled(true);
        }
    }

    public void switchToFirstFragment(String frag, int count){
        ft=fm.beginTransaction();
        if(frag.equals("left")){
            Bundle args=new Bundle();
            args.putString("left1","Left f1 "+count);
            lf1=new LeftFragmentOne();
            lf1.setArguments(args);
            ft.replace(R.id.left_frag,lf1);
            ft.addToBackStack(null);
        }
        else
        {
            Bundle args=new Bundle();
            args.putString("right1","Right f1 "+count);
            rf1=new RightFragmentOne();
            rf1.setArguments(args);
            ft.replace(R.id.right_frag,rf1);
            ft.addToBackStack(null);
        }
        ft.commit();
    }

    public void switchToSecondFragment(String frag,int count){
        ft=fm.beginTransaction();
        if(frag.equals("left")){
            Bundle args=new Bundle();
            args.putString("left2","Left f2 "+count);
            lf2=new LeftFragmentTwo();
            lf2.setArguments(args);
            ft.replace(R.id.left_frag,lf2);
            ft.addToBackStack(null);
        }
        else
        {
            Bundle args=new Bundle();
            args.putString("right2","Right f2 "+count);
            rf2=new RightFragmentTwo();
            rf2.setArguments(args);
            ft.replace(R.id.right_frag,rf2);
            ft.addToBackStack(null);
        }
        ft.commit();
    }

    public void switchToThirdFragment(String frag,int count){
        ft=fm.beginTransaction();
        if(frag.equals("left")){
            Bundle args=new Bundle();
            args.putString("left3","Left f3 "+count);
            lf3=new LeftFragmentThree();
            lf3.setArguments(args);
            ft.replace(R.id.left_frag,lf3);
            ft.addToBackStack(null);
        }
        else
        {
            Bundle args=new Bundle();
            args.putString("right3","Right f3 "+count);
            rf3=new RightFragmentThree();
            rf3.setArguments(args);
            ft.replace(R.id.right_frag,rf3);
            ft.addToBackStack(null);
        }
        ft.commit();
    }

    private void loadFragments(){
        ft=fm.beginTransaction();
        Bundle args=new Bundle();
        args.putString("right1","Right f1");
        rf1=new RightFragmentOne();
        rf1.setArguments(args);
        args.putString("left1","Left f1");
        lf1 = new LeftFragmentOne();
        lf1.setArguments(args);
        ft.add(R.id.right_frag,rf1);
        ft.add(R.id.left_frag,lf1);
        ft.addToBackStack(null);
        ft.commit();
        loadFrags.setEnabled(false);
    }
}
