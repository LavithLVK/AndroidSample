package com.example.lvk.shawsank_prison.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lvk.shawsank_prison.R;
import com.example.lvk.shawsank_prison.database.PrisonerDBHeleper;
import com.example.lvk.shawsank_prison.recylcer.PrisonerModel;
import com.example.lvk.shawsank_prison.recylcer.PrisonerViewAdapter;

import java.util.ArrayList;

/**
 * Created by lavith.edam on 6/28/2017.
 */

public class ViewFragment extends Fragment {

    ArrayList<PrisonerModel> prisoners;
    PrisonerDBHeleper prisonerDBHeleper;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        prisonerDBHeleper=new PrisonerDBHeleper(getActivity(),2);
        return inflater.inflate(R.layout.view_fragment,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView=(RecyclerView)view.findViewById(R.id.recycler);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getActivity());
        prisoners = (ArrayList<PrisonerModel>) prisonerDBHeleper.getAllPrisoners();
        recyclerView.setAdapter(new PrisonerViewAdapter(prisoners));
        recyclerView.setLayoutManager(layoutManager);
    }
}
