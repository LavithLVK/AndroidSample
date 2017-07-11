package com.example.lvk.fragment_navigation;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TabHost;
import android.widget.TableLayout;

public class MainActivity extends AppCompatActivity {

    FragmentManager fm=getFragmentManager();
    TabLayout tabLayout;
    private int tabpos=0;
    private int tabprevpos=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout=(TabLayout)findViewById(R.id.tablayout);

        tabLayout.addTab(tabLayout.newTab().setText("Fragment1"));
        tabLayout.addTab(tabLayout.newTab().setText("Fragment2"));
        tabLayout.addTab(tabLayout.newTab().setText("Fragment3"));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tabpos=tab.getPosition();
                Fragment fragment=getFragment(tabpos);
                FragmentTransaction ft=fm.beginTransaction();
                Bundle args=new Bundle();
                args.putString("data","Fragment "+(tabpos+1));
                fragment.setArguments(args);
                if(fm.getBackStackEntryCount()>=1){
                    ft.replace(R.id.container,fragment);
                }
                else
                {
                    ft.add(R.id.container,fragment);
                }
                ft.addToBackStack(null);
                ft.commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tabprevpos=tab.getPosition();
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        tabLayout.getTabAt(tabprevpos);
    }

    private Fragment getFragment(int pos){
        switch(pos){
            case 0:
                Fragment1 f1=new Fragment1();
                return f1;
            case 1:
                Fragment2 f2=new Fragment2();
                return f2;
            case 2:
                Fragment3 f3=new Fragment3();
                return f3;
            default:
                return null;
        }
    }
}
