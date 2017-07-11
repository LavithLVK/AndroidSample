package com.example.lvk.sqlite_sample.prisoner_recylcer;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lvk.sqlite_sample.Prisoner;
import com.example.lvk.sqlite_sample.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lavith.edam on 6/22/2017.
 */

public class PrisonerListFragment extends Fragment{
    List<Prisoner> prisoners;
    RecyclerView recyclerView;
    Context ctx;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle args=getArguments();
        Gson gson=new Gson();
        prisoners=new ArrayList<Prisoner>();
        if(args!=null){
            ArrayList<String> json_list=args.getStringArrayList("prisoners");
            for(int i=0;i<json_list.size();i++){
                prisoners.add(gson.fromJson(json_list.get(i), Prisoner.class));
            }
        }
        return inflater.inflate(R.layout.list_prisoner,container,false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getActivity());
        recyclerView=(RecyclerView)view.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new PrisonerAdapter(prisoners,ctx));
    }


    public void setContext(Context ctx){
        this.ctx = ctx;
    }

}
