package com.example.lvk.recycler_view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        layoutManager.scrollToPosition(5);
        RecyclerView.LayoutManager layoutManager=new GridLayoutManager(this,2);
        setContentView(R.layout.activity_main);
        RecyclerView recycle=(RecyclerView)findViewById(R.id.recycler);
        recycle.setLayoutManager(layoutManager);
        recycle.setAdapter(new LVKAdapter(getModels()));
    }

    private ArrayList<DataModel> getModels(){
        ArrayList<DataModel> models= new ArrayList<>();
        for(int i=0;i<1500;i++){
            DataModel model = new DataModel();
            model.setFirst("FIRST"+(i+1));
            model.setSecond("SECOND"+(i+1));
            models.add(model);
        }
        return models;
    }
}
